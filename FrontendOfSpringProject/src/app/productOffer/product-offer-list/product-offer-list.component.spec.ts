import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductOfferListComponent } from './product-offer-list.component';

describe('ProductOfferListComponent', () => {
  let component: ProductOfferListComponent;
  let fixture: ComponentFixture<ProductOfferListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductOfferListComponent]
    });
    fixture = TestBed.createComponent(ProductOfferListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
