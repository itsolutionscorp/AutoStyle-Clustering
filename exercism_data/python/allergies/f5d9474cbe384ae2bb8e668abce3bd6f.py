allergy_list = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()

class Allergies:
    
    def __init__(self, number):
        self.list = []
        for a,b in zip(bin(number)[::-1], allergy_list):
            if a == '1':
                self.list.append(b)
            elif a == 'b':
                break

    def is_allergic_to(self, allergy):
        return allergy in self.list
