class Allergies:
    def __init__(self, score):
        self.score = score
        self.allergy_list = ['eggs','peanuts','shellfish',
                        'strawberries','tomatoes','chocolate',
                        'pollen','cats']
        self.list = []

        if score >= 2**8:
            self.score -= 2**8

        for i in reversed(range(0,8)):
            if self.score >= 2**i:
                self.list.insert(0,self.allergy_list[i])
                self.score -= 2**i        

    def is_allergic_to(self, str):
        for i in self.list:
            if str == i:
                return True
            
