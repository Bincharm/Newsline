1) Create Postgres database "Newsline" and write connection(datasource) data for it in application.properties.

2) In order to get war-file and launch in tomcat web server, build the project with maven in Intellij Idea.
   Launch the project (the roles and admin account will be created).

3) Credentials: admin, "123".
   You can log in as an admin on the page "Log in" or register as a user on the page "Registration"(you can register only as a user).

4) If you are authorized as the admin then you can go to "News (only for admin)" and create a news or update/delete an existing news, or view the full version (click the icons) of the news. This page is available only for admin.
   In addition you can go to "News (for all)" and view the total list of news or view the full version of each of them (click the image or the headline of news to see the full version). This page is available for any user or guest.
   
   Both pages contain pagination. There is possibility to regulate amount of news on a page.
   
