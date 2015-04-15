class Allergies:
    def __init__(self, listNumber):
        self.list = []
        
        listNumber %= 256
        
        if listNumber >= 128:
            self.list.append('cats')
            listNumber -= 128
            
        if listNumber >= 64:
            self.list.append('pollen')
            listNumber -= 64
            
        if listNumber >= 32:
            self.list.append('chocolate')
            listNumber -= 32
        
        if listNumber >= 16:
            self.list.append('tomatoes')
            listNumber -= 16
            
        if listNumber >= 8:
            self.list.append('strawberries')
            listNumber -= 8
            
        if listNumber >= 4:
            self.list.append('shellfish')
            listNumber -= 4
            
        if listNumber >= 2:
            self.list.append('peanuts')
            listNumber -= 2
            
        if listNumber >= 1:
            self.list.append('eggs')
            listNumber -= 1
            
        self.list.reverse()
            
    def is_allergic_to(self, allergicTo):
        for allergen in self.list:
            if allergen is allergicTo:
                return True
        return False
