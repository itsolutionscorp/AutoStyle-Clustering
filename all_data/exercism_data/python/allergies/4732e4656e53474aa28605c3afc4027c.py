class Allergies():

    def __init__(self, score):
        allergy_list = [
            'cats',
            'pollen',
            'chocolate',
            'tomatoes',
            'strawberries',
            'shellfish',
            'peanuts',
            'eggs']
        binary_lookup = format(self.score, '08b')

        self.score = score % 256
        self.list = [allergy for allergy in allergy_list if bool(int(binary_lookup[allergy_list.index(allergy)]))]
        self.list.reverse()


    def is_allergic_to(self, allergen):
        return allergen in self.list
