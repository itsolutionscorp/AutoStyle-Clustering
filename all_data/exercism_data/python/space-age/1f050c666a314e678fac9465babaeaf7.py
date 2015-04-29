class SpaceAge():

    secPerYear = 31557600.0

    def __init__( self, sec ):
        self.seconds = sec

    def on_earth_digits( self ):
        return self.seconds / self.secPerYear
    
    def on_earth( self ):
        return round( self.on_earth_digits(), 2 )

    def on_mercury( self ):
        return round( self.on_earth_digits() / 0.2408467, 2 )

    def on_venus( self ):
        return round( self.on_earth_digits() / 0.61519726, 2 )

    def on_mars( self ):
        return round( self.on_earth_digits() / 1.8808158, 2 )

    def on_jupiter( self ):
        return round( self.on_earth_digits() / 11.862615, 2 )

    def on_saturn( self ):
        return round( self.on_earth_digits() / 29.447498, 2 )

    def on_uranus( self ):
        return round( self.on_earth_digits() / 84.016846, 2 )

    def on_neptune( self ):
        return round( self.on_earth_digits() / 164.79132, 2 )

    

    
