import { Component, OnInit } from '@angular/core';
import { Car } from 'src/app/models/Car'
import { CarService } from 'src/app/services/car.service';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  constructor(private http: CarService) { }

  ngOnInit(): void {
  }

  make:string = '';
  model:string = '';
  makeModel:string = '';
  result:string = '';

  getCars() {
    this.http.getAllCars().subscribe(
      (response) => {
        console.log(response);
      }
    )
  }

  searchCars() {

    if (this.make.length == 0 && this.model.length > 0) {
      this.getCarByModel()
      this.result  = 'Search results for: ' + this.model
    } else if (this.make.length > 0 && this.model.length == 0) {
      this.getCarsByMake()
      document.getElementById("carData")?.innerHTML == 'Search results for Make: ' + this.make
    } else if (this.make.length > 0 && this.model.length > 0) {
      this.makeModel = this.make + '&model=' + this.model
      this.getCarsByMakeAndModel()
      document.getElementById("carData")?.innerHTML + 'Search results for: ' + this.make + ' ' + this.model 

    }
  }

  getCarsByMake() {
    this.http.getCarByMake(this.make).subscribe(
      (response) => {
        console.log(response);
      }
    )
  }

  getCarByModel() {
    this.http.getCarByModel(this.model).subscribe(
      (response) => {
        console.log(response);
      }
    )
  }

  getCarsByMakeAndModel() {
    this.http.getCarByMakeModel(this.makeModel).subscribe(
      (response) => {
        console.log(response);
      }
    )
  }

  searchResults() {
  }

}
