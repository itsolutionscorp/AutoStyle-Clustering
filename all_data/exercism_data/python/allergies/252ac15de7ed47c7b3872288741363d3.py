#
# captainnurple's code for the Python Hamming Distance exercise.
#
# Because bitwise operations are fun!
# Treat the score as bits!!
# Then the bits themselves become bools for if that allergy exists
# So if the score is 34 it's 00100010 in binary
# Which turns into flags for the second and sixths items in the list, peanuts and chocolate!!

class Allergies:

    knownAllergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, allergyVal):
        self.allergyVal = allergyVal
        self.list = []

        for i, allergen in enumerate(self.knownAllergens):
            if(self.get_bit(allergyVal,i)):
                self.list.append(allergen)

    def is_allergic_to(self, allergen):
        return allergen in self.list

    def get_bit(self, byteVal,index):
        return ((byteVal&(1<<index))!=0);
