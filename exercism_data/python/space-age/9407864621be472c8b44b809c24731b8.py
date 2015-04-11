class SpaceAge():
    """Write a program that, given an age in seconds, calculates how old someone is in terms of a given planet's solar years."""
    
    def __init__(self, seconds):
        self.seconds = seconds
    
    def on_earth(self):
        return round(float(self.seconds)/float(31557600), 2)
    def on_mercury(self):
        return round(float(self.seconds)/float(31557600)/0.2408467, 2)
    def on_venus(self):
        return round(float(self.seconds)/float(31557600)/0.61519726, 2)
    def on_mars(self):
        return round(float(self.seconds)/float(31557600)/1.8808158, 2)
    def on_jupiter(self):
        return round(float(self.seconds)/float(31557600)/11.862615, 2)
    def on_saturn(self):
        return round(float(self.seconds)/float(31557600)/29.447498, 2)
    def on_uranus(self):
        return round(float(self.seconds)/float(31557600)/84.016846, 2)
    def on_neptune(self):
        return round(float(self.seconds)/float(31557600)/164.79132, 2)
