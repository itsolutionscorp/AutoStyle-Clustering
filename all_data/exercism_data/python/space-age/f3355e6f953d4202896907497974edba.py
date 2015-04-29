class SpaceAge:
    def __init__(self, seconds):
        self.seconds = seconds
        self.earth_sec = 31557600
        self.Me_orbitp = 0.2408467
        self.Ve_orbitp = 0.61519726
        self.Ma_orbitp = 1.8808158
        self.Ju_orbitp = 11.862615
        self.Sa_orbitp = 29.447498
        self.Ur_orbitp = 84.016846
        self.Ne_orbitp = 164.79132

    def _convert_secs(self, orbitp):
        days_sec = ((orbitp * 100) / 100 * self.earth_sec)
        return days_sec

    def on_earth(self):
        return round(self.seconds / self.earth_sec, 2)

    def on_mercury(self):
        dsec = self._convert_secs(self.Me_orbitp)
        return round(self.seconds / dsec, 2)

    def on_venus(self):
        dsec = self._convert_secs(self.Ve_orbitp)
        return round(self.seconds / dsec, 2)

    def on_mars(self):
        dsec = self._convert_secs(self.Ma_orbitp)
        return round(self.seconds / dsec, 2)

    def on_jupiter(self):
        dsec = self._convert_secs(self.Ju_orbitp)
        return round(self.seconds / dsec, 2)

    def on_saturn(self):
        dsec = self._convert_secs(self.Sa_orbitp)
        return round(self.seconds / dsec, 2)

    def on_uranus(self):
        dsec = self._convert_secs(self.Ur_orbitp)
        return round(self.seconds / dsec, 2)

    def on_neptune(self):
        dsec = self._convert_secs(self.Ne_orbitp)
        return round(self.seconds / dsec, 2)
