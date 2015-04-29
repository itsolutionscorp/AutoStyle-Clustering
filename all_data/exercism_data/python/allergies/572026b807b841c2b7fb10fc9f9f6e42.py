class Allergies(object):

    allergen_list = [
        "eggs", "peanuts", "shellfish", "strawberries", 
        "tomatoes", "chocolate", "pollen", "cats"
        ]
        
    def __init__(self, score):
        self.score = score
    
    def is_allergic_to(self, allergen):
        #shift the 1, or 'True' value over by the index value in allergen_list
        #use & operator to determine if allergen score is 'True'
        #works because scores are on base 2 scale, same as binary spots 
        return self.score & (1 << self.allergen_list.index(allergen))

    @property
    #'getter' for list of allergens attribute for Allergies class
    def list(self):
        return [allergen for allergen in self.allergen_list
                if self.is_allergic_to(allergen)]
