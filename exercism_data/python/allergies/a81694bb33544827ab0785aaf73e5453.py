class Allergies:
    
    def __init__(self, score):
        self.score = score

        allergyscores = { 
                    0: 'eggs',
                    1: 'peanuts',
                    2: 'shellfish',
                    3: 'strawberries',
                    4: 'tomatoes',
                    5: 'chocolate',
                    6: 'pollen',
                    7: 'cats'
                    }

        
        self.list = []        
        
        if len(bin(self.score)[2:]) > 8:
            binscore = list(bin(self.score)[3:])
        else:
            binscore = list(bin(self.score)[2:])

        for i in range(1, len(binscore)+1):
            if binscore[-i] == '1':
                self.list.append(allergyscores[i-1])

    def is_allergic_to(self, allergen):    
        if allergen in self.list:
            return True
        else:
            return False
