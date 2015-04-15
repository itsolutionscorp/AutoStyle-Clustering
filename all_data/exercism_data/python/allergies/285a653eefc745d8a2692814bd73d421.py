#based on a list return whether a score has certain allergy
class Allergies:  
    alargens = {1:"eggs",2:"peanuts",4:"shellfish",8:"strawberries",16:"tomatoes",32:"chocolate",64:"pollen",128:"cats"}
    list = []
    
    def __init__(self,s):
        leftPoints = s
        start = s
        
        while(start >= 1):
            if(leftPoints >= start):
                leftPoints -= start
                self.list.append(self.alargens[start])
    
            start = start/2
    
        self.list.reverse()

    def is_allergic_to(self,allergy):
        return allergy in self.list



#print Allergies(0).list
