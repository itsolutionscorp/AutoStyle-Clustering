########################################################################
class Allergies:
    """"""    
    #----------------------------------------------------------------------
    def __init__(self, num):
        """Constructor"""
        self.als_list = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']
        self.list = []

        for al in self.als_list:
            if (num & (2**self.als_list.index(al)))>0:
                self.list.append(al)
    
    def is_allergic_to(self, allergy):
        return (allergy in self.list)
