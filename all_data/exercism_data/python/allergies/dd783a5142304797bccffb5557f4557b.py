class Allergies:
    allergie_scores = {
        'eggs' : 1,
        'peanuts' : 2,
        'shellfish' : 4,
        'strawberries' : 8,
        'tomatoes' : 16,
        'chocolate' : 32,
        'pollen' : 64,
        'cats' : 128 
        }

    def __init__(self, score_sum):
        self.score_sum = score_sum%256
        self.list = []
        #eggs should be added to the list only if score_sum is not an even number
        if self.score_sum%2==1:
                self.list.insert(0,(1,'eggs'))
                self.score_sum -= 1
        
        for key,value in self.allergie_scores.items():
            if not value == 1:
                self.score_sum = self.score_sum - value
                if self.score_sum < 0 or self.score_sum == value:
                    self.score_sum += value
                else:
                    self.list.insert(0,(value,key))
        #this is required to pass the test cases
        self.list = [j for i,j in sorted(self.list)]

    def is_allergic_to(self,allergen):
        return allergen in self.list
