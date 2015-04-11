

class Allergies:
    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.flag = '{0:08b}'.format(score)[::-1]
        self.list = [self.allergens[i] for i in range(8) if self.flag[i] == '1']

    def is_allergic_to(self, case):
        return case in self.list
