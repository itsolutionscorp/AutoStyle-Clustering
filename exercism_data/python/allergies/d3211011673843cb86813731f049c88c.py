class Allergies:

    allergies = ["eggs","peanuts","shellfish","strawberries","tomatoes","chocolate","pollen","cats"]

    def __init__(self, allergy_level):
        self._level = allergy_level

        self.list = []

        for i in range(len(self.allergies)):
            if allergy_level & 2**i:
                self.list.append(self.allergies[i])



    def is_allergic_to(self, allergen):
        return allergen in self.list

   
