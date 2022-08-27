import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'dev-bw6pqg8v.us.auth0.com',
            redirectUrl: window.location.origin,
            clientId: 'H22kSCyclmrYnnj1QxHm57XwAfsnmbnw',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
