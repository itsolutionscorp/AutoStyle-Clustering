class Allergies():

    def __init__(self, value):
        self.score = value % 256
        allergens = [
            'cats',
            'pollen',
            'chocolate',
            'tomatoes',
            'strawberries',
            'shellfish',
            'peanuts',
            'eggs'
        ]
        binary_score = format(self.score, '08b')
        self.list = [allergy for allergy in allergens if bool(int(binary_score[allergens.index(allergy)]))]
        self.list.reverse()


    def is_allergic_to(self, allergen):
        return allergen in self.list
