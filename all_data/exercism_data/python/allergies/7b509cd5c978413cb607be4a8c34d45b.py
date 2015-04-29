class Allergies(object):
    def __init__(self, score):
        self.score = score
        # list allergens per binary representation
        allergens = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs']
        # ignore non-allergen score parts with modulo
        # convert score to 8 char binary string, where 1 is true and 0 is false
        self.binary_score = bin(self.score % 256)[2:].zfill(8)
        # add allergen to list if char is 'true'
        self.list = [allergens[i] for i in reversed(range(8)) if self.binary_score[i] == '1']

    # return true if person has a given allergen
    def is_allergic_to(self, allergen):
        is_allergic = False
        for result in self.list:
            if result == allergen:
                is_allergic = True
        return is_allergic
