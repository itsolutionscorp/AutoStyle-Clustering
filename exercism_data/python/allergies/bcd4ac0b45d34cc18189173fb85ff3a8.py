class Allergies:

    allergies_mapping = { 1:"eggs",2:"peanuts",4:"shellfish",8:"strawberries",16:"tomatoes",32:"chocolate",64:"pollen",128:"cats" }
    allergies = ["eggs","peanuts","shellfish","strawberries","tomatoes","chocolate","pollen","cats"]

    def __init__(self, allergy_level):
        self._level = allergy_level

        self.list = []

        for i in range(len(self.allergies)):
            if allergy_level & 2**i:
                self.list.append(self.allergies[i])



    def is_allergic_to(self, allergen):
        return allergen in self.list

   
