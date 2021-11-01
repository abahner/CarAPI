import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
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
  trim:string = '';
  year:number = 0;
  price:number = 0;
  mileage:number = 0;

  getCars() {
    this.http.getAllCars().subscribe(
      (response) => {
        console.log(response);
      }
    )
  }

  searchCars() {
    this.http.getAllCars().subscribe (
      (response) => {
        if (this.make !== '') {
          response = response.filter(car => car.make === this.make)
        }
        if (this.model !== '') {
          response = response.filter(car => car.model === this.model)
        }
        if (this.trim !== '') {
          response = response.filter(car => car.trim === this.trim)
        }
        if (this.year > 0) {
          response = response.filter(car => car.year === this.year)
        }
        if (this.price > 0) {
          response = response.filter(car => car.price === this.price)
        }
        if (this.mileage > 0) {
          response = response.filter(car => car.mileage === this.mileage)
        }
        console.log(response);
      }
    )
  }

}
