'''
Created on Oct 2, 2014

@author: dulshani
'''

class Allergies:
    allergy_scores = {1:"eggs", 2:"peanuts", 4:"shellfish", 8:"strawberries", 16:"tomatoes", 32:"chocolate", 
                      64:"pollen", 128:"cats"}
    list=[]
    
    def __init__ (self, score):
        self.score = score
        self.set_list()
    
    def is_allergic_to(self, allergy):
        if allergy in self.list:
            return True
        else:
            return False
    
    def set_list(self):
        allergy_list = []
        if self.score<= 255:
            total_score = self.score
        else:
            total_score = self.score - 256
        for key in sorted(self.allergy_scores.iterkeys(), reverse=True):
            if key <= total_score:
                total_score -= key
                allergy_list.append(self.allergy_scores[key])
        self.list = allergy_list[::-1]
