class Allergies:

    def scoring(self,current_score,check_score):
        if current_score == 1:
            return ['eggs']
        elif current_score == 0:
            return []
        elif current_score >= check_score:
            return [self.foods[check_score]] + self.scoring(current_score - check_score, check_score/2)
        else:
            return self.scoring(current_score, check_score/2)


    def __init__(self,score): 
        self.foods = {128: 'cats',
            64 : 'pollen',
            32 : 'chocolate',
            16 : 'tomatoes',
            8 : 'strawberries',
            4: 'shellfish',
            2: 'peanuts',
            1: 'eggs'
            }
        self.score = score % 256 
        self.list = self.scoring(self.score,max(self.foods.keys()))[::-1] 
        # calculate allergies from max of 128 downwards and reverse list

    def is_allergic_to(self,food):
        return food in self.list



if __name__ == '__main__':
    a = Allergies(255)
    print a.score
    print a.list
    print a.is_allergic_to('peanuts')
    print a.is_allergic_to('cats')
