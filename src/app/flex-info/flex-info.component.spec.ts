import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlexInfoComponent } from './flex-info.component';

describe('FlexInfoComponent', () => {
  let component: FlexInfoComponent;
  let fixture: ComponentFixture<FlexInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FlexInfoComponent]
    });
    fixture = TestBed.createComponent(FlexInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
