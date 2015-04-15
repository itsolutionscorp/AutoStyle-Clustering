numbers = [128, 64, 32, 16, 8, 4, 2, 1]
allergies = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs']
converter = dict(zip(numbers, allergies))

class Allergies(object):
    def __init__(self, allergies):
        while allergies > 255:
            allergies -= 256
        self.allergies = allergies
        self.list = self.list_allergies()
        
    def list_allergies(self):
        allergy_list = []
        allergy = self.allergies
        
        for number in numbers:
            if allergy >= number:
                allergy_list.append(converter[number])
                allergy -= number
                
        allergy_list.reverse()
        
        return allergy_list
        
    def is_allergic_to(self, allergy):
        return allergy in self.list
