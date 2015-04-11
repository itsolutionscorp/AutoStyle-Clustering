class SpaceAge:
    def __init__(self, age_in_seconds):
        self.seconds = age_in_seconds

    def on_mercury(self):
        return self.get_relative_age(self.seconds, 0.2408467)

    def on_venus(self):
        return self.get_relative_age(self.seconds, 0.61519726)

    def on_earth(self):
        return self.get_relative_age(self.seconds, 1)

    def on_mars(self):
        return self.get_relative_age(self.seconds, 1.8808158)

    def on_jupiter(self):
        return self.get_relative_age(self.seconds, 11.862615)

    def on_saturn(self):
        return self.get_relative_age(self.seconds, 29.447498)

    def on_uranus(self):
        return self.get_relative_age(self.seconds, 84.016846)

    def on_neptune(self):
        return self.get_relative_age(self.seconds, 164.79132)

    def on_pluto(self):  # Save Pluto!
        return self.get_relative_age(self.seconds, 247.68)

    @staticmethod
    def get_relative_age(age_in_seconds, orbital_period):
        return round(age_in_seconds / (31557600.0 * orbital_period), 2)
