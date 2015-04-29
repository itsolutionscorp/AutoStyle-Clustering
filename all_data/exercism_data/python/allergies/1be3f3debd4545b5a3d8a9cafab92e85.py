class Allergies:
    def __init__(self, num=0):
        a = ['eggs',
             'peanuts',
             'shellfish',
             'strawberries',
             'tomatoes',
             'chocolate',
             'pollen',
             'cats'
            ]
        b = str(bin(num % 2**len(a)))[2:]
        self.list = [a[i-1] for i in range(1, 1 + len(b)) if b[-i] == '1']
    
    def is_allergic_to(self, allergen):
        return allergen in self.list
