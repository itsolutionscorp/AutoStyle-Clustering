allergy_score = {1:'eggs', 2:'peanuts', 4:'shellfish',8:'strawberries',
                 16:'tomatoes',32:'chocolate',64:'pollen',128:'cats'}
                 #    USE BINARY!!!


class Allergies:
    """a class that contains the 'is_allergic_to' method """

    def __init__(self, num):

        self.num = num
        self.testing_allergies = []
        while num > 255:
            num-=256
    
        for i in [2**n for n in range(7,-1,-1)]:
            if num-i>=0:
                self.testing_allergies.append(allergy_score[i])
                num-=i
        if num==1:
                self.testing_allergies.append(allergy_score[1])
        return
    
    
    def is_allergic_to(self, allergen):
        if allergen in self.testing_allergies:
            return True
        return False
		
    def list(self):
        return self.testing_allergies[::-1]

    


