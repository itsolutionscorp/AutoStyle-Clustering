class Allergies:

    dct = {1:'eggs', 2:'peanuts', 4:'shellfish' ,8:'strawberries' ,16:'tomatoes' ,32:'chocolate' ,64:'pollen' ,128:'cats'}

    def __init__(self, num):
        self.val = num
        self.list = []
        keys = self.dct.keys()
        keys.sort()
        for k in keys:
            if self.val & k:
                self.list.append(self.dct[k])

    def is_allergic_to(self, word):
        for al in self.list:
            if al == word:
                return True
        return False
