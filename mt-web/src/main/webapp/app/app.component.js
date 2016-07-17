import { Component } from '@angular/core';
import {Routes, ROUTER_DIRECTIVES} from '@angular/router';

export class AppComponent {

	constructor() {
	}

}

AppComponent.annotations = [
                            	new Component({
                            		selector: 'mt-app',
                            		template: '<div><router-outlet>Router outlet</router-outlet></div>'
                            	})
];