###im iterating over a descending order by value
###example: what = 129
###129-128 = 1
###1 - 64 = -63
###1 - 32 = -31
###...
###1-1 = 0
###only the numbers that got succesfully deducted get in the list (128,1)

class Allergies:
    numbers = {
        'eggs' : 1,
        'peanuts' : 2,
        'shellfish' : 4,
        'strawberries' : 8,
        'tomatoes' : 16,
        'chocolate' : 32,
        'pollen' : 64,
        'cats' : 128 
        }
    def __init__(self, what):
        self.what = what%256
        self.list = []
        for key,value in reversed(sorted([(value,key) for (key,value) in self.numbers.items()])):
            
            self.what = self.what - key
            if self.what < 0:
                self.what += key
            else:
                self.list.insert(0,value)

    def is_allergic_to(self,allergen):
        return allergen in self.list
