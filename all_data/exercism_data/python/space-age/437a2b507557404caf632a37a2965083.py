__author__ = 'Momo'


class SpaceAge:
    def __init__(self, age_in_seconds):
        self._age_in_seconds = age_in_seconds

    def __getattr__(self, name):

        age = lambda x : round (self._age_in_seconds/x, 2)

        lambda_dict = {'seconds': self._age_in_seconds,
                       'on_earth': lambda: age(31557600),
                       'on_mercury': lambda: age(0.2408467*31557600),
                       'on_venus': lambda: age(0.61519726*31557600),
                       'on_mars': lambda: age(1.8808158*31557600),
                       'on_jupiter': lambda: age(11.862615*31557600),
                       'on_saturn': lambda: age(29.447498*31557600),
                       'on_uranus': lambda: age(84.016846*31557600),
                       'on_neptune': lambda: age(164.79132*31557600)
        }

        try:
            return lambda_dict[name]
        except:
            raise AttributeError
