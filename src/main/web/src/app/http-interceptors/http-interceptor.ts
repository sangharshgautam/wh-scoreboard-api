import { Injectable } from "@angular/core";
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpResponse,
  HttpErrorResponse
} from "@angular/common/http";
import { Observable } from "rxjs";
import { tap } from 'rxjs/operators';
import {Router} from "@angular/router";
import {NzNotificationService} from "ng-zorro-antd/notification";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  constructor(private router: Router, private notification: NzNotificationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(tap(
      event => event instanceof HttpResponse ? 'succeeded' : '',
      err => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.router.navigate(['login']);
          }
        }
        this.createNotification('error', err)
        // this.openSnackBar('danger', "Something went wrong with the request, please try again.");
      }
    ))
  }
  createNotification(type: string, error: HttpErrorResponse): void {
    this.notification.create(
      'error',
      error.error.error,
      error.error.message
    );
  }
}
