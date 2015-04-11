class SpaceAge(object):
    _base_period = 365.25 * 60 * 60 * 24
    _periods = (
        ("earth", 1.0),
        ("mercury", 0.2408467),
        ("venus", 0.61519726),
        ("mars", 1.8808158),
        ("jupiter", 11.862615),
        ("saturn", 29.447498),
        ("uranus", 84.016846),
        ("neptune", 164.79132),
    )    

    def __init__(self, seconds):
        self.seconds = seconds

    def _calc(self, period):
        return round(self.seconds / (self._base_period * period), 2)

    @classmethod
    def _init_class(cls):
        for name, period in cls._periods:
            cls._add_period_method(name, period)   

    @classmethod
    def _add_period_method(cls, name, period):
        def on_body(self):
            return self._calc(period)

        on_body.__name__ = "on_{name}".format(name=name)
        setattr(cls, on_body.__name__, on_body)     

SpaceAge._init_class()
