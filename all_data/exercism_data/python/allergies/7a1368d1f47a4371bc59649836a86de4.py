
class Allergies:

    # Create a list of allergens indicated by the allergy "score.""
    def __init__(self, score):

        # Create a list of known allergens.
        allergens =['eggs', 'peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']

        # Convert "score" to binary number string.
        # Convert string to a list, using only the characters from position 2 onwards.
        # Reverse the order of the list.
        # Zip this list, and the allergen list together into a list of tuples.
        scoreAllergenTuple = zip(list(bin(score)[2:])[::-1], allergens)

        # Use comprehension to remove tuples whose 1st value is 0.
        # This leaves only those items the "score" 
        self.list = [x[1] for x in scoreAllergenTuple if x[0] != '0']

    # Indicate whether a the score indicates an allergy to a given allergen.
    def is_allergic_to(self, allergens):
        isAllergic = False
        if allergens in self.list:
            isAllergic = True
        return isAllergic
