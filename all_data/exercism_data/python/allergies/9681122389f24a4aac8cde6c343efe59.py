# allergies.py

from collections import OrderedDict

score_key = OrderedDict([(128, 'cats'),
                         (64, 'pollen'),
                         (32, 'chocolate'),
                         (16, 'tomatoes'),
                         (8, 'strawberries'),
                         (4, 'shellfish'),
                         (2, 'peanuts'),
                         (1, 'eggs')])


class Allergies(object):

        def __init__(self, score):
            self.score = score
            allergies = []
            scorel = self.score
            if scorel > 256:
                scorel -= 256  # ignore non allergen parts of score
            for key in score_key:
                if scorel >= key:
                    allergies.append(score_key[key])
                    scorel -= key
            else:
                self.list = sorted(allergies)

        def is_allergic_to(self, allergen):
            if self.list == []:
                return False
            elif allergen in self.list:
                return True
            else:
                return False
