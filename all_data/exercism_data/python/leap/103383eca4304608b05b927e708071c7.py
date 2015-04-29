class Year:
    def __init__(self, anno):
        self.year = anno

    def is_leap_year(self):
        return self._is_mundane_leap() and not self._is_century() or self._is_exceptional_century()

    def _is_mundane_leap(self):
        return self.year % 4 == 0

    def _is_century(self):
        return self.year % 100 == 0

    def _is_exceptional_century(self):
        return self.year % 400 == 0
