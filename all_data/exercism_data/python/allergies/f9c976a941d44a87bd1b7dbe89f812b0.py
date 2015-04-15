
class Allergies:

    def __init__(self, score):

        allergens =['eggs', 'peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']
        scoreAllergenTuple = zip(list(bin(score)[2:])[::-1], allergens)
        self.list = [x[1] for x in scoreAllergenTuple if x[0] != '0']

    def is_allergic_to(self, allergens):
        isAllergic = False
        if allergens in self.list:
            isAllergic = True
        return isAllergic
