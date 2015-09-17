# chart-tests

Project testing out different features in Vaadin Charts' Scatter chart
- Test different amount of data points and performance
- Draw vector graphics on top of graph
- Trac zoom events on the client side to be able to undo them

## Copy paste this code to checkout project and run locally
```git clone https://github.com/Peppe/chart-tests.git
cd chart-tests
mvn jetty:run
``
and open browser to http://localhost:8080

## More detailed instructions

1. Check out project
2. Install a license from https://vaadin.com/pro/licenses
3. Call mvn package in root folder
4. Call mvn jetty:run in root folder
5. Go to http://localhost:8080 in browser

## Or, in IntelliJ IDEA
3. Import project (as Maven or existing project should both work
4. Open up "Maven projects in top right corner
5. double click chart-tests -> Lifecycle -> package
6. double click chart-tests -> Plugins -> jetty -> jetty:run
7. Go to http://localhost:8080 in browser
