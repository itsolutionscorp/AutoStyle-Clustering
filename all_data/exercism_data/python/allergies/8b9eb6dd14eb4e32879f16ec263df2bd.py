import copy

class Allergies(object):

    def __init__(self, score):
        self.allergy_scores = zip('eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split(),
                                  [2**i for i in range(8)])
        #self.allergy_dict = dict(self.allergy_scores)

        self.score = score
        self.list = self.makeAllergyList()

    def is_allergic_to(self,food):
        return food in self.list

    def makeAllergyList(self):
        if self.score > 255:
            return ['eggs']
        else:
            res = []
            score = self.score
            foods = copy.deepcopy(self.allergy_scores)
            foods.reverse()
            for food in foods:
                if food[1] <= score and score > 0:
                    res.append(food[0])
                    score -= food[1]

            res.reverse()
            return res
