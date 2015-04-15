#need list to maintain order to pass
allergies_list = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']

allergies = {
    'eggs'	        : 1,  
    'peanuts'		: 2,  
    'shellfish'		: 4,  
    'strawberries'	: 8,  
    'tomatoes'		: 16, 
    'chocolate'		: 32, 
    'pollen'		: 64, 
    'cats'	        : 128,
    }

class Allergies():
    def __init__(self, value):
        self.allergies = value

    def is_allergic_to(self, item):
        return bool(allergies[item] & self.allergies)

    def __get_allergies(self):
        return [a for a in allergies_list if bool(allergies[a] & self.allergies) == True]
    
    list = property(__get_allergies)
