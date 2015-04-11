#
# Returns allergies based on component sum of allergy scores for foods.
#

class Allergies:
    def __init__(self, score):
        self.score = score
        self.list = []
        self.allergens = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs']

        # Powers of 2 (binary) reveal individual allergies
        self.allergic_to = [int(i) for i in format(self.score, '08b')]
        if self.score > 256:
            self.list = ['eggs']
        else:
            self.list = [self.allergens[i] for i in range(7,-1,-1) if self.allergic_to[i]]

    def is_allergic_to(self, food):
        return food in self.list
