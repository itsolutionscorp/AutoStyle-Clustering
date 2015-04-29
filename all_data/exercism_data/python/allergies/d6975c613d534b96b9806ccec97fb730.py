from collections import OrderedDict

class Allergies:
    allergy_scores = OrderedDict([("eggs", 1),
                                  ("peanuts", 2),
                                  ("shellfish", 4),
                                  ("strawberries", 8),
                                  ("tomatoes", 16),
                                  ("chocolate", 32),
                                  ("pollen", 64),
                                  ("cats", 128)])

    def __init__(self, score):
        self._score = score
        self.list = [a_item for a_item, a_score in Allergies.allergy_scores.items()
                     if score & a_score]
        
    def is_allergic_to(self, allergen):
        return Allergies.allergy_scores[allergen] & self._score
