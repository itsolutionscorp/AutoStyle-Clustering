'''
allergies.py

Find the allergies given an allergy score
'''

class Allergies(object):

    ALLERGENS = [
        ('eggs', 1),
        ('peanuts', 2),
        ('shellfish', 4),
        ('strawberries', 8),
        ('tomatoes', 16),
        ('chocolate', 32),
        ('pollen', 64),
        ('cats', 128)
    ]

    def __init__(self, score):
        self.list = [a for a, s in self.ALLERGENS if score & s]

    def is_allergic_to(self, allergen):
        return allergen in self.list
