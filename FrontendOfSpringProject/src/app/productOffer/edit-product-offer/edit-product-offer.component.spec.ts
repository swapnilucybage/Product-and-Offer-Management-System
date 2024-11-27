import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProductOfferComponent } from './edit-product-offer.component';

describe('EditProductOfferComponent', () => {
  let component: EditProductOfferComponent;
  let fixture: ComponentFixture<EditProductOfferComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditProductOfferComponent]
    });
    fixture = TestBed.createComponent(EditProductOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
