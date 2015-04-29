items = ['eggs',
         'peanuts',
         'shellfish',
         'strawberries',
         'tomatoes',
         'chocolate',
         'pollen',
         'cats']

class Allergies():
    def __init__(self, score):
        self.score = score
        self.list = [item for i, item in enumerate(items)
                         if (2**i & self.score) == 2**i]

    def is_allergic_to(self, item):
        return item in self.list
