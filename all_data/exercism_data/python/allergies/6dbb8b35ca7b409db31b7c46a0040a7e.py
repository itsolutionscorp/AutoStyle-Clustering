__author__ = 'Arno'

class Allergies(object):

    def __init__(self, score):
        self.score = score
        self.allergies = {'eggs': False,
                 'peanuts': False,
                 'shellfish': False,
                 'strawberries': False,
                 'tomatoes': False,
                 'chocolate': False,
                 'pollen': False,
                 'cats': False}
        self.list = []
        counter = score
        if counter in range(128,256):
            self.allergies['cats'] = True
            self.list.append('cats')
            counter -= 128
        if counter in range(64,128):
            self.allergies['pollen'] = True
            self.list.append('pollen')
            counter -= 64
        if counter in range(32,64):
            self.allergies['chocolate'] = True
            self.list.append('chocolate')
            counter -= 32
        if counter in range(16,32):
            self.allergies['tomatoes'] = True
            self.list.append('tomatoes')
            counter -= 16
        if counter in range(8,16):
            self.allergies['strawberries'] = True
            self.list.append('strawberries')
            counter -= 8
        if counter in range(4,8):
            self.allergies['shellfish'] = True
            self.list.append('shellfish')
            counter -= 4
        if counter in range(2,4):
            self.allergies['peanuts'] = True
            self.list.append('peanuts')
            counter -= 2
        if counter == 1:
            self.allergies['eggs'] = True
            self.list.append('eggs')
        self.list.reverse()


    def is_allergic_to(self, allergen):
        return self.allergies[allergen]
