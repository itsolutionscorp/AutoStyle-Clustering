'''exer allergies'''

class Allergies(object):
    '''handle allergies, convert scored to human readable information
    and give some basic querying capabilities'''
    allergens = [
        ('cats', 128),
        ('pollen', 64),
        ('chocolate', 32),
        ('tomatoes', 16),
        ('strawberries', 8),
        ('shellfish', 4),
        ('peanuts', 2),
        ('eggs', 1),
    ]

    def __init__(self, score):
        # guard score
        score = score % 256
        self.score = score
        self.allergic_to = []
        # build list of allergies
        for allergen in Allergies.allergens:
            name, val = allergen
            if val <= score:
                score -= val
                # insert onto list, since we're processing them backwards
                self.allergic_to.insert(0, name)

    def is_allergic_to(self, thing):
        '''is allergic to thing?'''
        return thing in self.allergic_to

    @property
    def list(self):
        '''returns a list of things allergic to'''
        return self.allergic_to
