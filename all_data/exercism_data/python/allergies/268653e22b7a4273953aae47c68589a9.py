allergens = ["eggs",
             "peanuts",
             "shellfish",
             "strawberries",
             "tomatoes",
             "chocolate",
             "pollen",
             "cats"]

allergies_list = lambda x: [allergens[i] for i in filter(lambda y: (x>>y)%2,
                                                         range(len(allergens)))]

class Allergies():
    def __init__(self, code):
        self.list = allergies_list(code)

    def is_allergic_to(self, allergen):
        return allergen in self.list
