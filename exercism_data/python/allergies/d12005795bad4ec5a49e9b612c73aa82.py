#
# Returns allergies based on component sum of allergy scores for foods.
#

class Allergies:
    def __init__(self, score):
        self.score = score

        self.allergens = {1:'eggs',
                          2:'peanuts',
                          4:'shellfish',
                          8:'strawberries',
                          16:'tomatoes',
                          32:'chocolate',
                          64:'pollen',
                          128:'cats'}

        # Bitwise & to determine if a particular allergen is a component
        # of the allergy score.
        self.list = [self.allergens[i] for i in sorted(self.allergens) if self.score & i]

    def is_allergic_to(self, food):
        return food in self.list
