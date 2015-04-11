class Allergies():
    def __init__(self, number):
        score = number
        if score>256:
            score -= 256
        self.allergy_score = bin(score)[2:].zfill(8)
        self.list=[]
        allergies = ['cats','pollen','chocolate','tomatoes','strawberries','shellfish','peanuts','eggs']
        for i in range(0,8):
            if self.allergy_score[i]=='1':
                self.list.append(allergies[i])
        self.list=list(reversed(self.list))

    def is_allergic_to(self, l):
        return l in self.list
