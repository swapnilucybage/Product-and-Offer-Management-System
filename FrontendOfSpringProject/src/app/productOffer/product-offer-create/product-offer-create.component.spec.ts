import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductOfferCreateComponent } from './product-offer-create.component';

describe('ProductOfferCreateComponent', () => {
  let component: ProductOfferCreateComponent;
  let fixture: ComponentFixture<ProductOfferCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductOfferCreateComponent]
    });
    fixture = TestBed.createComponent(ProductOfferCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
