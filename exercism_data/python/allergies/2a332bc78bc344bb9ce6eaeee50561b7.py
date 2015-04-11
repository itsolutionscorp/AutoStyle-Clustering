

class Allergies(object):


    def __init__(self, params):
        self.allergins = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        isal = [x for x in reversed(format(params, '010b'))]
        self.list = [w for x,w in enumerate(self.allergins) if isal[x] == '1']
        
    def is_allergic_to (self,name):
        if name in self.list:
            return True
        return False
