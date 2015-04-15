#
#
#

class Allergies:
    # Constructor
    def __init__(self,score):
        self.list = []
        self.allergy_ref = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']
        for i in range(0,8):
            if ((1 << i) & score) != 0:
                self.list.append(self.allergy_ref[i])
    # Find in list
    def is_allergic_to(self,item):
        return (item in self.list)
