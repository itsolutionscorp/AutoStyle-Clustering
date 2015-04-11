class SpaceAge:


    def __init__( self, seconds ):
        self.planet_years = {'Earth'    : 1,
                              'Mercury' : 0.2408467,
                              'Venus'   : 0.61519726,
                              'Mars'    : 1.8808158,
                              'Jupiter' : 11.862615,
                              'Saturn'  : 29.447498,
                              'Uranus'  : 84.016846,
                              'Neptune' : 164.79132}
        self.seconds = seconds

    def on_earth( self ):
        return round(self.seconds / self.to_seconds(self.planet_years['Earth']), 2)

    def on_mercury( self ):
        return round(self.seconds / self.to_seconds(self.planet_years['Mercury']), 2)

    def on_venus( self ):
        return round(self.seconds / self.to_seconds(self.planet_years['Venus']), 2)

    def on_mars( self ):
        return round(self.seconds / self.to_seconds(self.planet_years['Mars']), 2)

    def on_jupiter( self ):
        return round(self.seconds / self.to_seconds(self.planet_years['Jupiter']), 2)

    def on_saturn( self ):
        return round(self.seconds / self.to_seconds(self.planet_years['Saturn']), 2)

    def on_uranus( self ):
        return round(self.seconds / self.to_seconds(self.planet_years['Uranus']), 2)

    def on_neptune( self ):
        return round(self.seconds / self.to_seconds(self.planet_years['Neptune']), 2)
    
    def to_seconds( self, earth_years ):
        return (earth_years * float(31557600)) # 60 * 60 * 24 * 365.25
