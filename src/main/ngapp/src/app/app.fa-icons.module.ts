import { NgModule } from '@angular/core'

import { library } from '@fortawesome/fontawesome-svg-core';
import { faSearch, faAngleDoubleLeft, faAngleLeft, faAngleRight, faAngleDoubleRight } from '@fortawesome/free-solid-svg-icons';

@NgModule({})
export class FontAwesomeIconesModule {
	constructor() {
		library.add(faSearch, faAngleDoubleLeft, faAngleLeft, faAngleRight, faAngleDoubleRight);
	}
}
