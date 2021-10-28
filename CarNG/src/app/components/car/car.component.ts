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

  getCars() {
    this.http.getAllCars().subscribe(
      (response) => {
        console.log(response);
      }
    )
  }

  searchCars() {
    if (this.model === "" && this.make !== "") {
      this.http.searchCars("make", this.make).subscribe(
        (response) => {
          if (this.trim !== "") {
            response = response.filter(car => car.trim === this.trim)
          }
          console.log(response)
          
        }
      )
    } else if (this.model !== "" || this.trim !== "") {
      this.http.searchCars("model", this.model).subscribe(
        (response) => {

          if (this.trim !== "" && this.model !== "") {
            response = response.filter(car => car.trim === this.trim)
            console.log(response)

          } else if (this.make === "" && this.model === "") {
            this.http.searchCars("trim", this.trim).subscribe(
              (response) => {
                console.log(response)

              }
            )
          } else if (this.make !== "") {
            response = response.filter(car => car.make === this.make)
            console.log(response)

          } else {
            console.log(response)
          }

        }
      )
    }
  }
}
