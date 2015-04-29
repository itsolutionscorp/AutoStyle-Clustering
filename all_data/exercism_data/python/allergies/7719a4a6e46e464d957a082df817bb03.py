allergens = ( # need a tuple here because the test depends on order
    "eggs",
    "peanuts",
    "shellfish",
    "strawberries",
    "tomatoes",
    "chocolate",
    "pollen",
    "cats"
)
allergen_scores = {}
curr_value = 0x1
for allergen in allergens:
    allergen_scores[allergen] = curr_value
    curr_value *= 2 # each allergen is twice the previous, making for easy bit math below


class Allergies(object):
    def __init__(self, allergy_score):
        self.list = [ a for a in allergens if allergen_scores[a] & allergy_score != 0 ]

    def is_allergic_to(self, allergen):
        return allergen in self.list
