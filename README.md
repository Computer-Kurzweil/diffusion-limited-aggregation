# diffusion-limited-aggregation

![Java CI with Maven](https://github.com/Computer-Kurzweil/diffusion-limited-aggregation/workflows/Java%20CI%20with%20Maven/badge.svg) 
[![Build Status](https://travis-ci.com/Computer-Kurzweil/diffusion-limited-aggregation.svg?branch=master)](https://travis-ci.com/Computer-Kurzweil/diffusion-limited-aggregation)

Fractals and random walk due to Brownian motion cluster

## Abstract

Diffusion-limited aggregation (DLA) is the process whereby particles undergoing a random walk due to Brownian motion cluster together to form aggregates of such particles.

This theory, proposed by T.A. Witten Jr. (not to be confused with Edward Witten) and L.M. Sander in 1981,[1] is applicable to aggregation
in any system where diffusion is the primary means of transport in the system. DLA can be observed in many systems such as electrodeposition,
Hele-Shaw flow, mineral deposits, and dielectric breakdown.

The clusters formed in DLA processes are referred to as Brownian trees. These clusters are an example of a fractal.

## Wikipedia
* [https://en.wikipedia.org/wiki/Diffusion-limited_aggregation](https://en.wikipedia.org/wiki/Diffusion-limited_aggregation)

## Screenshots

![The Dendrite](src/main/resources/img/screen1.png)

![The Dendrite after a while](src/main/resources/img/screen2.png)

## Run the Desktop Application
```
git clone https://github.com/Computer-Kurzweil/diffusion-limited-aggregation.git
cd diffusion-limited-aggregation
./gradlew clean shadowJar runShadow
```

## Run the Applet Test
```
git clone https://github.com/Computer-Kurzweil/diffusion-limited-aggregation.git
cd diffusion-limited-aggregation
TODO: xxx
```
