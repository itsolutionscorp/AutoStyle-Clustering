class Allergies:
    
        
    
    def __init__(self, score):
        self.score = score
        self.list = []
        self.type_allergies = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]
        self.fill_list()
    
    def is_allergic_to(self,all):
        return all in self.list
    
    def fill_list(self):
        b = self.def_binary(self.score)
        for a,i in enumerate(b):
            if i == 1:
                self.list.append(self.type_allergies[a])
    
    def def_binary(self,sc):
        binary = []
        while (sc != 0):
            binary.append(sc%2)
            sc /= 2
        return binary[:8]
