class Allergies:
    def __init__(self, score, index=[[128, 'cats'],
                                     [64,  'pollen'],
                                     [32,  'chocolate'],
                                     [16,  'tomatoes'],
                                     [8,   'strawberries'],
                                     [4,   'shellfish'],
                                     [2,   'peanuts'],
                                     [1,   'eggs']]):
        self.initList(score,index)

    def initList(self, score, index):
        ''' initializes allergy list according to score. '''
        self.index  = index
        max_score   = sum([pair[0] for pair in self.index])
        allergies   = []
        while(score > 0):
            for pair in self.index:
                (index, allergy) = pair
                if (score >= index):
                    #-------------------------------------------#
                    # this is a bad idea, but the exercise asks #
                    # for the program to discard non-allergens. # 
                    if (score > max_score):                     #
                        score = score % (max_score+1)           #
                    #-------------------------------------------#
                    elif (allergy in allergies):
                        # it would be better to do this:
                        raise IndexError("Invalid allergy score.")
                        score -= index
                        continue
                    else:
                        #print(allergy + " added.")
                        allergies.append(allergy)
                        score -= index
                        continue
        allergies.reverse()

        self.list = allergies

    def is_allergic_to(self, allergy):
        ''' passthrough wrapper function for isAllergicTo
            (I just prefer camelCasing)
        '''
        return self.isAllergicTo(allergy)

    def isAllergicTo(self, allergy):
        return (allergy in self.list)
