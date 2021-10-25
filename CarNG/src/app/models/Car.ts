export class Car{
    id: number
    year: number
    make: string
    model: string
    trim: string
    mileage: number
    price: number
    img: string

    constructor(id: number, year: number, make: string, model: string, trim: string, mileage: number, price: number, img: string){
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.trim = trim;
        this.mileage = mileage;
        this.price = price;
        this.img = img;
    }

}