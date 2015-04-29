_scores = { 1:'eggs', 2:'peanuts',4:'shellfish',8:'strawberries',16:'tomatoes',32:'chocolate',64:'pollen',128:'cats'}

class Allergies(object):

    def __init__(self,score):
        self.list = []

        strBinary = self.toBinary(score)
        lenght = len(strBinary)
        self.list = [_scores[2**i] for i in range(0,lenght) if int(strBinary[lenght-1-i])==1]

    def is_allergic_to(self,key):
        return (key in self.list)
    
    def toBinary(self,score):
        strSc = ""
        while score/2>=0.5: strSc = str(score%2) + strSc; score = int(score/2)
        return strSc[-8:]
