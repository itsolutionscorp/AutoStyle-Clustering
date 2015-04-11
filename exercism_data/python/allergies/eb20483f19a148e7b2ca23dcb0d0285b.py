from collections import OrderedDict


class Allergies:
    names = ['eggs', 
        'peanuts', 
        'shellfish', 
        'strawberries', 
        'tomatoes', 
        'chocolate', 
        'pollen', 
        'cats']
    scores = {'eggs': 1, 
        'peanuts': 2, 
        'shellfish' : 4, 
        'strawberries' : 8, 
        'tomatoes' : 16, 
        'chocolate' : 32, 
        'pollen' : 64, 
        'cats' : 128}

    def __init__(self, score):
        self.total_score = score

    def is_allergic_to(self, allergen):
        return bool(self.total_score & self.scores[allergen])

    @property
    def list(self):
        return [allergen
                for allergen in self.names
                if self.is_allergic_to(allergen)]
