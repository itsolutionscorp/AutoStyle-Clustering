allergens = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats" ]

class Allergies:
    def __init__(self, score):	
        self._list = [ allergens[idx] for idx,v in enumerate(allergens) if score & 1 << idx]

    @property
    def list(self):
        return self._list
	
    def is_allergic_to(self, allergen):
        return allergen in self._list
