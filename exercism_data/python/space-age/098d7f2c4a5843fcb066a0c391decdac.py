# William Morris
# exercism.io
# space_age.py

class SpaceAge:

    def __init__(self,seconds):
        self.seconds = seconds
        planets = ['Earth', 'Mercury', 'Venus', 'Mars', 'Jupiter', 'Saturn',
                   'Uranus', 'Neptune']
        # periods given in Earth years
        periods = [1.0,0.2408467,0.61519726,1.8808158,11.862615,29.447498,
                   84.016846,164.79132]
        # convert periods from Earth years to seconds and store in a dictionary
        self.planets = dict(zip(planets,[period*31557600 for period in periods]))

        # dynamically create the self.on_planet methods (ex. self.on_earth())
        def set_on_planet_name(self,planet,name):
            def _on_planet():
                return round((self.seconds/self.planets[planet]),2)
            setattr(self,name,_on_planet)
        for planet in self.planets:
            _name = 'on_'+planet.lower()
            set_on_planet_name(self,planet,_name)
            
