import { Component } from '@angular/core';
import * as Papa from 'papaparse';

@Component({
  selector: 'app-spending-list',
  templateUrl: './spending-list.component.html',
  styleUrls: ['./spending-list.component.css']
})
export class SpendingListComponent {
  dataList!: any[];

  onChange(files: File[]) {
    if (files[0]) {
      Papa.parse(files[0], {
        header: true,
        skipEmptyLines: true,
        complete: (result: { data: any; }, file: any) => {
          this.dataList = result.data
        }
      })
    }
  }
}
