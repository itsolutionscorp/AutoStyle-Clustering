class Allergies:
    possible_allergens = { 1:'eggs',
                  2:'peanuts',
                  4:'shellfish',
                  8:'strawberries',
                  16:'tomatoes',
                  32:'chocolate',
                  64:'pollen',
                  128:'cats'}
    def __init__(self, test_values):
        self.test_values = test_values

    def is_allergic_to(self,allergen):
        for key, value in self.possible_allergens.items():
            if key <= self.test_values and allergen == value:
                return True
