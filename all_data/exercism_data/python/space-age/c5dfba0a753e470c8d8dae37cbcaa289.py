class Planet:
        Earth = 1
        Mercury = 2
        Venus = 3
        Mars = 4
        Jupiter = 5
        Saturn = 6
        Uranus = 7
        Neptune = 8

class SpaceAge:
    EarthYear = 31557600.0
    yearLen = { Planet.Earth: EarthYear,
                Planet.Mercury: 0.2408467 * EarthYear,
                Planet.Venus: 0.61519726 * EarthYear,
                Planet.Mars: 1.8808158 * EarthYear,
                Planet.Jupiter: 11.862615 * EarthYear,
                Planet.Saturn: 29.447498 * EarthYear,
                Planet.Uranus: 84.016846 * EarthYear,
                Planet.Neptune: 164.79132 * EarthYear }
    
    def __init__(self, seconds):
        self.seconds = seconds

    def __ageHelper(self, planet):
        return round(self.seconds/SpaceAge.yearLen[planet], 2)
    
    def on_earth(self):
        return self.__ageHelper(Planet.Earth)
    
    def on_mercury(self):
        return self.__ageHelper(Planet.Mercury)

    def on_venus(self):
        return self.__ageHelper(Planet.Venus)

    def on_mars(self):
        return self.__ageHelper(Planet.Mars)

    def on_jupiter(self):
        return self.__ageHelper(Planet.Jupiter)

    def on_saturn(self):
        return self.__ageHelper(Planet.Saturn)

    def on_uranus(self):
        return self.__ageHelper(Planet.Uranus)

    def on_neptune(self):
        return self.__ageHelper(Planet.Neptune)
