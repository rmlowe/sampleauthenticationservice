This project provides a sample authentication service for use with [NetDimensions Talent Suite](http://www.netdimensions.com/talent-suite/), implemented as a Java web application. Note that this code is intended primarily to demonstrate the delegated authentication protocol. It does **not** validate user credentials against a real directory service or database.

You may adapt this code to create your own authentication service for Talent Suite.

# Deployment #

The contents of the `WebContent` directory constitute an exploded WAR file, which you can deploy to your application server in the usual way.

For example, if you are using Apache Tomcat, you can copy the contents of this directory to a directory named, say, `sampleauthenticationservice` under your installation's `webapps` directory.

You will need to modify the values of two context parameters in `WEB-INF/web.xml` as follows:

  * `ekpBase` will need to point to the base URL of your Talent Suite installation; and
  * `key` will need to contain the secret key value used to compute the MD5 cryptographic hash for the response.

**Note that Talent Suite uses a session cookie to track state between the original request for the protected page, and the response from the authentication service. It is therefore essential that the hostname in `ekpBase` exactly matches the hostname that users use to access the Talent Suite site.**

# Configuration #

To configure the sample authentication service, set the properties listed below in `WEB-INF/conf/ekp.properties`.
  * `authentication.key` should be set to the same value as the `key` context parameter mentioned above.
  * `authentication.service.url` should be set to point to `/sso` in your deployment of the sample authentication service. For example, if your application server is running on `https://www.example.com/` and the content path for the web application is `/sampleauthenticationservice`, then `authentication.service.url` should be set to `https://www.example.com/sampleauthenticationservice/sso`.