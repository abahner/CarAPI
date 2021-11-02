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
  year:string = '';
  price:string = '';
  mileage:string = '';
  carsList : Car[] = [];

  getCars() {
    this.http.getAllCars().subscribe(
      (response) => {
        console.log(response);
        this.carsList = response
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
        if (this.year !== '') {
          response = response.filter(car => car.year === parseInt(this.year))
        }
        if (this.price !== '') {
          response = response.filter(car => car.price === parseInt(this.price))
        }
        if (this.mileage !== '') {
          response = response.filter(car => car.mileage === parseInt(this.mileage))
        }
        console.log(response);
        this.carsList = response
      }
    )
  }

}
