class Allergies:
    def __init__(self, score):
        self.table = {1 : 'eggs',
                      2 : 'peanuts',
                      4 : 'shellfish',
                      8 : 'strawberries',
                      16 : 'tomatoes',
                      32 : 'chocolate',
                      64 : 'pollen',
                      128 : 'cats'}
                      
        self.list = self.compute_list(score % 256)
                      

    def compute_list(self, score):
        
        out_list = []

        for key in sorted(self.table.keys(), reverse=True):
            
            if score - key >= 0:
                score -= key
                out_list.append(self.table[key])
        
        return out_list[::-1]
        
    def is_allergic_to(self, allergen):
        return allergen in self.list
