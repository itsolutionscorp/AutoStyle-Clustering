class Allergies:

    def __init__(self, score):
        self.score = score
        self.list = []
        self.allergen_dict = {
            'eggs':1,
            'peanuts':2,
            'shellfish':4,
            'strawberries':8,
            'tomatoes':16,
            'chocolate':32,
            'pollen':64,
            'cats':128
        }
        self.get_allergens_from_score()

    @staticmethod
    def base2_numbers_from_int(number):
        base2_numbers = [256,128,64,32,16,8,4,2,1]
        base2_composition = []
        for base2 in base2_numbers:
            if number >= base2:
                number -= base2
                base2_composition.append(base2)
        return base2_composition

    def get_allergens_from_score(self):
        number_list = self.base2_numbers_from_int(self.score)
        for key,value in self.allergen_dict.iteritems():
            if value in number_list:
               self.list.append(key)
        self.list = sorted(self.list, key=self.allergen_dict.get)


    def is_allergic_to(self,food):
        if food in self.list:
            return True
        else:
            return False
