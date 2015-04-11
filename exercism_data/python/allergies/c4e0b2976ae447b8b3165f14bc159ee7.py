class Allergies:
    def __init__(self,alnum):
        self.list = []
        if alnum >= 256:
            alnum -= 256
        if alnum >= 128:
            self.list.append('cats')
            alnum -= 128
        if alnum >= 64:
            self.list.append('pollen')
            alnum -= 64
        if alnum >= 32:
            self.list.append('chocolate')
            alnum -= 32
        if alnum >= 16:
            self.list.append('tomatoes')
            alnum -= 16
        if alnum >= 8:
            self.list.append('strawberries')
            alnum -= 8
        if alnum >= 4:
            self.list.append('shellfish')
            alnum -= 4
        if alnum >= 2:
            self.list.append('peanuts')
            alnum -= 2
        if alnum >= 1:
            self.list.append('eggs')
            alnum -= 1
        self.list.reverse()
    def is_allergic_to(self,allergy):
        if self.list.count(allergy) > 0:
            return True
        else:
            return False
