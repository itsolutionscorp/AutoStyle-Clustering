class SpaceAge:

    def __init__(self, seconds):
        self.seconds = seconds
        self.earth_years = seconds / 31557600.0

    def get_years_to_hundreth(self, years_to_earth_years):
        return round(self.earth_years / years_to_earth_years, 2)

    def on_earth(self):
        return self.get_years_to_hundreth(1)

    def on_venus(self):
        return self.get_years_to_hundreth(.61519626)
    
    def on_mars(self):
        return self.get_years_to_hundreth(1.8808158)

    def on_mercury(self):
        return self.get_years_to_hundreth(.2408467)

    def on_neptune(self):
        return self.get_years_to_hundreth(164.79132)

    def on_saturn(self):
        return self.get_years_to_hundreth(29.447498)

    def on_uranus(self):
        return self.get_years_to_hundreth(84.016846)

    def on_jupiter(self):
        return self.get_years_to_hundreth(11.862615)
