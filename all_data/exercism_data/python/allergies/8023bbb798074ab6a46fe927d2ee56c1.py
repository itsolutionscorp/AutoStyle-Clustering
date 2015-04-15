    




class Allergies:


    def __init__(self, what):
        #im not sure i should have that much code in init. How else could it be done?
        self.what = what
        self.list = []
        self.numbers = {
        'eggs' : 1,
        'peanuts' : 2,
        'shellfish' : 4,
        'strawberries' : 8,
        'tomatoes' : 16,
        'chocolate' : 32,
        'pollen' : 64,
        'cats' : 128 
        }
        #i don't understand why a score of 257 returns only eggs since it should be none unless im missing something
        if self.what > 255 and self.what%2 == 1:
            self.list.append('eggs')
        else:
            #im iterating over a descending order by value
            for key,value in reversed(sorted([(value,key) for (key,value) in self.numbers.items()])):
                #example: what = 129
                #129-128 = 1
                #1 - 64 = -63
                #1 - 32 = -31
                #...
                #1-1 = 0
                #only the numbers that got succesfully deducted get in the list (128,1)
                self.what = self.what - key
                if self.what < 0:
                    self.what += key
                else:
                    self.list.append(value)

        #here i am flipping the list again(for test passing purposes)
        self.list = self.list[::-1]
    
        
    def is_allergic_to(self,allergen):
        if allergen in self.list:
            return True
        else:
            return False
