allergies = {
    'eggs' : 1,
    'peanuts' : 2,
    'shellfish' : 4,
    'strawberries' : 8,
    'tomatoes' : 16,
    'chocolate' : 32,
    'pollen' : 64,
    'cats': 128
    }

sorted_allergies = sorted(allergies.items(), key=lambda tup: tup[1])

class Allergies:

    def __init__(self, allergy_count):
        self.allergy_count = allergy_count
        
        self.list = []

        for i in sorted_allergies:
            if self.is_allergic_to(i[0]):
                self.list.append(i[0])


                

    def is_allergic_to(self, allergy):
        return (self.allergy_count & allergies[allergy]) > 0

    
        
