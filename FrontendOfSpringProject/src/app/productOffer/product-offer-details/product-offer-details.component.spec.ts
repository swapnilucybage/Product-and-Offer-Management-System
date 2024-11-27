import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductOfferDetailsComponent } from './product-offer-details.component';

describe('ProductOfferDetailsComponent', () => {
  let component: ProductOfferDetailsComponent;
  let fixture: ComponentFixture<ProductOfferDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductOfferDetailsComponent]
    });
    fixture = TestBed.createComponent(ProductOfferDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
