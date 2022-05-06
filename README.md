# Patrick's comments:

You can check this running in my personal Heroku Cloud
-> https://protected-anchorage-64469.herokuapp.com/
It's running via Heroku's `Hobby Dev free Dynos`

And `Hobby Dev postgresql`

Developing this simple app and deploying to Heroku's infra was useful for me to mature some Developer tech skills.

I also made a lot of research about why I'd use the tutorial's libs or another.

Ex: the tutorial uses compojure and lein, which I'm not much of a fan of any.

Pedestal vs compojure would apply some more unnecessary interceptors complexity in routers.

But probably Pedestal would be a better lib for real-world application.

Also, for clojurescript fullstack apps, reitit would be a better choice.

And for SQL manipulation, clojure.java.jdbc is simpler and data oriented, whilst hugsql would let us write plain SQL.


Maybe the next step would be adding a Docker postgres config 

For running this locally, currently you'd need a local postgres configuration.

https://wiki.archlinux.org/title/PostgreSQL#Installation

https://www.youtube.com/watch?v=qw--VYLpxG4

https://www.liquidweb.com/kb/change-a-password-for-postgresql-on-linux-via-command-line/

http://makble.com/clojure-and-postgresql-how-to-get-start

Some more research links for beginners:
http://www.learningclojure.com/2013/01/getting-started-with-ring.html

https://www.hugsql.org/

https://docs.microsoft.com/en-us/aspnet/web-api/overview/security/preventing-cross-site-request-forgery-csrf-attacks

https://www.youtube.com/watch?v=vrjgD0azkCw

https://github.com/ring-clojure/ring-anti-forgery

https://stackoverflow.com/questions/4852768/how-does-antiforgerytoken-work

https://www.gertgoet.com/2019/02/06/deploying-a-tools-deps-clojure-project-to-heroku.html

https://devcenter.heroku.com/articles/clojure-support#overriding-build-behavior



# Shouter's Heroku's Doc

A simple Clojure web app, using [ring](https://github.com/ring-clojure/ring), [compojure](https://github.com/weavejester/compojure) and [hiccup](https://github.com/weavejester/hiccup).  Short text messages called "shouts" can be entered and the last 100 shouts are shown on the page.

Shout text strings are stored in a [postgres](http://www.postgresql.org/) database

## Deploy via Heroku button

You can deploy your own copy of the Shouter app on Heroku via [![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

> If you do not have an account on Heroku, you will be promted to create one for free

## Deploying on Heroku manually

To deploy the Shouter Clojure app on Heroku, first clone the project using `git clone clone-URL`, the clone-URL is underneat the repository menu on the right hand side of this page.

Assuming you have Heroku Toolbelt installed, run `heroku create` from within the directory created by the previous clone command.

Add a postgres database to your heroku app using the command:

    heroku addons:add heroku-postgresql

> This command adds a free Heroku postgres database plan

Now deploy the Shouter web app to Heroku using the command:

    git push heroku master

## License

Copyright Aaron Bedra

Distributed under the Eclipse Public License, the same as Clojure.