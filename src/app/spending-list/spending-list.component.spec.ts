import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingListComponent } from './spending-list.component';

describe('SpendingListComponent', () => {
  let component: SpendingListComponent;
  let fixture: ComponentFixture<SpendingListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SpendingListComponent]
    });
    fixture = TestBed.createComponent(SpendingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
