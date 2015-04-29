# Allergies Python Exercism, 1st iteration

class Allergies(object):
    allergens = ["eggs","peanuts","shellfish","strawberries","tomatoes","chocolate","pollen","cats"]

    def __init__(self,score):
        self.score = score
        # A food is in the allergy list if 2 ** (index of food) & score is non-zero, since all scores are powers of 2
        self.list = [food for food in self.allergens if 2 ** self.allergens.index(food) & self.score > 0]
    
    def is_allergic_to(self,food):  
        return food in self.list
