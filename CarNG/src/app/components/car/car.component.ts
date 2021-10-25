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

  input:number = 0;
  
  getCars() {
    this.http.getAllCars().subscribe(
      (response) => {
        console.log(response);
        
      }
    )
  }

}
