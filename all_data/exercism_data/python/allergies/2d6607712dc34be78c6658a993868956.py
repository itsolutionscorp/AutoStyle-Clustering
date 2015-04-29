class Allergies():
    
    def __init__(self, score):
        allergies = list('{0:08b}'.format(score)[-8:])
        allergens = ['cats','pollen','chocolate','tomatoes','strawberries',
                     'shellfish','peanuts','eggs']
                
        self.list = ([y for x, y in zip(allergies,allergens)
                     if int(x)][::-1])

    def is_allergic_to(self, allergen):
        return allergen in self.list
