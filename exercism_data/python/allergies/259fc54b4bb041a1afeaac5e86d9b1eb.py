class Allergies(object):
    """docstring for Allergies"""
    """This problem was begging for bitwise operators,
    despite it not being the most readable or clear
    way of going about things. I did it this way for the sake
    of practicing."""
    """
    """
    def __init__(self, allergy_number):
        self.allergy_number = allergy_number
        self.full_allergen_list = [
            'eggs', 'peanuts', 'shellfish',
            'strawberries', 'tomatoes', 'chocolate',
            'pollen', 'cats'
        ]
        self.list = self.list_allergies()

    def is_allergic_to(self, allergen):
        return allergen in self.list

    def list_allergies(self):
        allergen_list = []
        for i in range(len(self.full_allergen_list)):
            if self.allergy_number & (1 << i) != 0:
                allergen_list.append(self.full_allergen_list[i])
        return allergen_list
