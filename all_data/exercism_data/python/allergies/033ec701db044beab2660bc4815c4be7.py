class Allergies(object):

    aList = ('eggs', 'peanuts', 'shellfish', 'strawberries',
            'tomatoes', 'chocolate', 'pollen', 'cats')

    def __init__(self,score):

        self.aInd = bin(score)[:1:-1][:8]
        self.list = [self.aList[i] for i,ind in enumerate(self.aInd) if ind == '1']

    def is_allergic_to(self,aStr):
        return aStr in self.list
