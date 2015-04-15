class Allergies:
    def __init__(self, num=0):
        self.num = num % 256
        self.b = str(bin(self.num))[2:]
        a = dict(enumerate(('eggs',
                            'peanuts',
                            'shellfish',
                            'strawberries',
                            'tomatoes',
                            'chocolate',
                            'pollen',
                            'cats'
                           ), start=1))
        self.list = [a[i] for i in range(1, 1 + len(self.b)) if self.b[-i] == '1']
    
    def is_allergic_to(self, allergen):
        return allergen in self.list
