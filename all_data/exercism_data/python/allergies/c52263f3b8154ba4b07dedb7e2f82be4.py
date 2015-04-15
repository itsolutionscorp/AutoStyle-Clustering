class Allergies(object):
    def __init__(self, allergy_score):
        self.allergy_score = allergy_score
        self.allergies = {  'eggs':         0b00000001,
                            'peanuts':      0b00000010,
                            'shellfish':    0b00000100,
                            'strawberries': 0b00001000,
                            'tomatoes':     0b00010000,
                            'chocolate':    0b00100000,
                            'pollen':       0b01000000,
                            'cats':         0b10000000
        }
        self.list = self.list()



    def is_allergic_to(self, allergy):
        if self.allergy_score & self.allergies[allergy] == self.allergies[allergy]:
            return True
        else:
            return False

    def list(self):
        list_of_allergies = []
        for allergy in sorted(self.allergies, key=self.allergies.get):
            if self.is_allergic_to(allergy):
                list_of_allergies.append(allergy)
        return list_of_allergies
