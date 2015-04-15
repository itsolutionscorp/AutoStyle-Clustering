

class Allergies(object):


    def __init__(self, params):
        self.allergins = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        self.allergies = dict(zip(self.allergins,reversed(format(params, '010b'))))
        self.list = [x for x in self.allergins if self.allergies[x] == '1']
        
    def is_allergic_to (self,name):
        return self.allergies[name] == '1'
