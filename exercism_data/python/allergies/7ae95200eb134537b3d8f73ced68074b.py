allergens = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats" ]

class Allergies:
    def __init__(self, score):	
        bin_score = bin(score)[:1:-1] # convert to binary, reverse order
        self._list = [ allergens[idx] for idx,v in enumerate(bin_score) if v == '1' and idx < len(allergens)]

    @property
    def list(self):
        return self._list
	
    def is_allergic_to(self, allergen):
        return allergen in self._list
