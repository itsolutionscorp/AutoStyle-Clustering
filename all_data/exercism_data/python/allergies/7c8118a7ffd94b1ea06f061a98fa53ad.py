class Allergies:

    def __init__(self, total):
        self.all_allergies = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        allergy_bitmask =  bin(total).lstrip('0b').zfill(8)
        self.list = [self.all_allergies[i] for (i,x) in enumerate(self.all_allergies) if allergy_bitmask[-1 - i] == '1']

    def is_allergic_to(self, ailment):
        return ailment in self.list
