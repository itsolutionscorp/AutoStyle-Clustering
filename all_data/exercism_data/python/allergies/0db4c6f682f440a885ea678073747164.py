class Allergies(object):
    def __init__(self, allergy_score):
        self.allergy_score = allergy_score
        self.allergies = {  'eggs':         1,
                            'peanuts':      2,
                            'shellfish':    4,
                            'strawberries': 8,
                            'tomatoes':     16,
                            'chocolate':    32,
                            'pollen':       64,
                            'cats':         128
        }
        self.list = self.list()



    def is_allergic_to(self, allergy):
        return self.allergy_score & self.allergies[allergy]

    def list(self):
        list_of_allergies = []
        for allergy in sorted(self.allergies, key=self.allergies.get):
            if self.is_allergic_to(allergy):
                list_of_allergies.append(allergy)
        return list_of_allergies
