__author__ = 'grdunn'


class Allergies(object):

    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes',
                 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.list = self.list_allergens(score if score < 256 else score - 256)

    def list_allergens(self, score):
        """
        the (1 << i) & score is the tricky bit here, 1 << i creates a mask
        applying the & operator give us the power of two that's set (if 2**i is set)
        otherwise it's 0

        :param score:
        :rtype : list
        """
        allergies = []
        for allergen in [i for i in range(0, len(self.allergens)) if ((1 << i) & score)]:
            allergies.append(self.allergens[allergen])
        return allergies

    def is_allergic_to(self, allergen):
        return allergen in self.list
