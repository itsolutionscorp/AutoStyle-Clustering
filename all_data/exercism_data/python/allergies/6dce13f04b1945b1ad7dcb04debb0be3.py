allergydict = {1:'eggs', 2:'peanuts', 4:'shellfish', 8:'strawberries',
             16:'tomatoes', 32:'chocolate',64:'pollen',128:'cats'}

class Allergies:
    
    def __init__(self, score):
        self.score = score
        self.list = []
        # Skip for loop if score equates to a single allergy
        if self.score in allergydict:
            self.list.append(allergydict[score])
        # Check each binary digit of score to determine allergies
        else:
            for i in range(8):
                allergyint = self.score & (2**i)
                if allergyint: self.list.append(allergydict[allergyint])
    
    def is_allergic_to(self, allergy):
        return allergy in self.list
        
    
    
    
