__author__ = 'tracyrohlin'

class Allergies:
    def __init__(self, score):
        self.score = score
        self.allergy_values = {1: "eggs", 2: "peanuts", 4: "shellfish", 8: "strawberries",
                16: "tomatoes", 32: "chocolate", 64: "pollen", 128: "cats"}
        self.list = []
        self.allergen_list()

    def allergen_list(self):
        new_score = self.score
        test = self.allergy_values.keys()
        test.sort()
        test.reverse()
        for value in test:
            if new_score >= value:
                self.list.append(self.allergy_values[value])
                new_score -= value


    def is_allergic_to(self, allergen):
        return allergen in self.list
