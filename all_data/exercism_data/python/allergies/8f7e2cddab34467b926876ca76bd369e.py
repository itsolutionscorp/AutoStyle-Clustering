import math

allergy_ref = {
    1:'eggs'
    ,2:'peanuts'
    ,4:'shellfish'
    ,8:'strawberries'
    ,16:'tomatoes'
    ,32:'chocolate'
    ,64:'pollen'
    ,128:'cats'
    }
allergy_scores = list(sorted(allergy_ref.keys()))

class Allergies:
    def __init__(self,allergy_score):
         #if allergy_score over 255, format the score
         #to fit our allergy list format
        while allergy_score >=256:
            allergy_score-=256
        self.score = allergy_score
         #initiate a list, in which to house all of the
         #allergies, that we can call later.
        self.list = []
         #insert allergies 1 by 1 into the allergy list
        while allergy_score>0:
            score_index =  math.floor(math.log(allergy_score,2))
            allergy = allergy_ref.get(allergy_scores[score_index])
            self.list.append(allergy)
            allergy_score-=2**score_index
        self.list=self.list[::-1]
            
    def is_allergic_to(self,item):
        return item in self.list
