import { Component } from '@angular/core';

export class WizardStepComponent {

	constructor() {
	}

}

WizardStepComponent.annotations = [
                            	new Component({
                            		selector: 'wizard-step',
                            		templateUrl: 'app/wizard/wizard-steps/wizard-step.template.html',
                            		styleUrls:  ['app/wizard/wizard-steps/wizard-step.component.css']
                            	})
];
