'''
Created on Oct 29, 2014

@author: jbarni00
'''
class Allergies:

    def __init__(self, value):
        self.list = []       
        data = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats' ]
        for i in range(len(data)):
            if value & (1 << i):
                self.list.append( data[i])

    def is_allergic_to(self, allergyitem):
        return  allergyitem in self.list        
