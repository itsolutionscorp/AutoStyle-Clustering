class Allergies:

    allergies_mapping = { 1:"eggs",2:"peanuts",4:"shellfish",8:"strawberries",16:"tomatoes",32:"chocolate",64:"pollen",128:"cats" }
    

    def __init__(self, allergy_level):
        self._level = allergy_level

        self.list = []

        for item in sorted(self.allergies_mapping):
            if allergy_level & item: self.list.append(self.allergies_mapping[item])



    def is_allergic_to(self, allergen):
        return allergen in self.list

   
