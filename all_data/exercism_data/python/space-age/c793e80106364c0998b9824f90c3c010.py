def convert(period):
    def inner(self):
        return round(
            self.seconds / period,
            2
        )

    return inner

class SpaceAge(object):

    on_mercury = convert(7600530.24)
    on_venus = convert(19413907.2)
    on_earth = convert(31558149.76)
    on_mars = convert(59354294.4)
    on_jupiter = convert(374335776.0)
    on_saturn = convert(929596608.0)
    on_uranus = convert(2661041808.0)
    on_neptune = convert(5200418592.0)

    def __init__(self, seconds):
        self.seconds = seconds
