import math

class Allergies(object):
    SCORE = {  1: 'eggs',
               2: 'peanuts',
               4: 'shellfish',
               8: 'strawberries',
              16: 'tomatoes',
              32: 'chocolate',
              64: 'pollen',
             128: 'cats',
            }

    def __init__(self, score):
        self._score = score

    def _list(self):
        mode = self._score
        while True:
            if mode <= 0:
                break
            score = 2**int(math.log(mode, 2))
            if score in self.SCORE:
                yield self.SCORE.get(score, None)
            mode -= score

    @property
    def list(self):
        return list(self._list())[::-1]

    def is_allergic_to(self, item):
        return item in self.list
