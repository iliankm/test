import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageNotFoundComponent } from 'app/page-not-found/page-not-found.component';
import { ListComponent } from 'app/list/list.component';
import { DetailsComponent } from 'app/details/details.component';
import { WizardComponent } from 'app/wizard/wizard.component';

const routes = [
    { path: '', redirectTo: 'list/all', pathMatch: 'full'},
    { path: 'list/:filter', component: ListComponent },
    { path: 'details', component: DetailsComponent },
    { path: 'wizard', component: WizardComponent },
    { path: '**', component: PageNotFoundComponent }
];

export const routing = RouterModule.forRoot(routes);;