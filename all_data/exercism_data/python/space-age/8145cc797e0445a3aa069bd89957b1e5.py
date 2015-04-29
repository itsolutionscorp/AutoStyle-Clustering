class SpaceAge(object):
    def __init__(self, s):
        self.seconds = s

        items = [('Earth',31557600),
                 ('Mercury',31557600*0.2408467),
                 ('Venus',31557600*0.61519726),
                 ('Mars',31557600*1.8808158),
                 ('Jupiter',31557600*11.862615),
                 ('Saturn',31557600*29.447498),
                 ('Uranus',31557600*84.016846),
                 ('Neptune',31557600*164.79132)]
        
        for item in items:
            exec "self.on_" + item[0].lower() + "= lambda: " + str(round(float(self.seconds) / item[1],2))
