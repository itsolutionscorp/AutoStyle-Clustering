class Allergies:
    ''' Convert score to binary, match it up with the allergy items'''
    def __init__(self, score):
        self.items = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                      'tomatoes', 'chocolate', 'pollen', 'cats']
        self.bin_score = list(reversed(bin(score)[2:]))
        self.list = [x for x,i in zip(self.items, self.bin_score) if i=='1']

    def is_allergic_to(self, item):
        for food, yes_no in zip(self.items, self.bin_score):
            if food==item:
                return int(yes_no)
