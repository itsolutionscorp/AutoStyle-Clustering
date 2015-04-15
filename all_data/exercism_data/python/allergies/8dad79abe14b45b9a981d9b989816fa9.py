class Allergies:
    allergie_scores = {
        'eggs' : 1,
        'peanuts' : 2,
        'shellfish' : 4,
        'strawberries' : 8,
        'tomatoes' : 16,
        'chocolate' : 32,
        'pollen' : 64,
        'cats' : 128 
        }
    
    def __init__(self, score_sum):
        self.score_sum = score_sum
        self.list = self.get_allergen_list()

    def get_allergen_list(self):
        #all smaller numbers fit to each bigger number
        #go binary
        #it returns a list sorted according to their value on the allergie_scores dictionary
    return sorted([x for x in self.allergie_scores if self.is_allergic_to(x)],key=lambda i:self.allergie_scores[i])

    def is_allergic_to(self,allergen):
        #Here Python's way of seeing all integers bigger than 0 as True is of use
        return self.allergie_scores[allergen] & self.score_sum
