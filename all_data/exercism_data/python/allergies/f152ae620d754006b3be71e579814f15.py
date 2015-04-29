class Allergies:
    ''' Bitwise matching of score and enumerated allergenes '''
    def __init__(self, score):
        self.items =['eggs', 'peanuts', 'shellfish', 'strawberries',
                      'tomatoes', 'chocolate', 'pollen', 'cats']
        self.list = [allergene for i,allergene in enumerate(self.items)
                     if 2**i & score]

    def is_allergic_to(self, item):
        return item in self.list
