class Allergies(object):
    score_to_allergen = {
        1: 'eggs',
        2: 'peanuts',
        4: 'shellfish',
        8: 'strawberries',
        16: 'tomatoes',
        32: 'chocolate',
        64: 'pollen',
        128: 'cats'
    }

    def __init__(self, score):
        self._score = score
        self.list = []
        self.determine_allergens()
        print self.list

    def is_allergic_to(self, item):
        return item in self.list

    def determine_allergens(self):
        for score, allergen in sorted(self.__class__.score_to_allergen.items(), key=lambda i: i[0]):
            if self._score & score:
                self.list.append(allergen)
