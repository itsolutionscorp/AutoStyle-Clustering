class SpaceAge:
    times = {
        'mercury':0.2408467,
        'venus':0.61519726,
        'mars':1.8808158,
        'jupiter':11.862615,
        'saturn':29.447498,
        'uranus':84.016846,
        'neptune':164.79132
    }
    def __init__(self,time):
        self.seconds = time
        
        
    def on_earth(self):
        self.earthtime = self.seconds/31557600
        return round(self.earthtime,2)
    def on_mercury(self):
        return round(self.earthtime/self.times['mercury'],2)
    def on_venus(self):
        return round(self.earthtime/self.times['venus'],2)
    def on_mars(self):
        return round(self.earthtime/self.times['mars'],2)
    def on_jupiter(self):
        return round(self.earthtime/self.times['jupiter'],2)
    def on_saturn(self):
        return round(self.earthtime/self.times['saturn'],2)
    def on_uranus(self):
        return round(self.earthtime/self.times['uranus'],2)
    def on_neptune(self):
        return round(self.earthtime/self.times['neptune'],2)
