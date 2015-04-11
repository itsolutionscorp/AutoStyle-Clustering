class Allergies(object):
    
    def __init__(self,allergy_score):
        num_to_allergen = {1:'eggs',2:'peanuts',4:'shellfish',8:'strawberries',
                       16:'tomatoes',32:'chocolate',64:'pollen',128:'cats'}
        Allergies.score = allergy_score % 256
        Allergies.list = []
        for x in [2**i for i in range(7,-1,-1)]:
            if (Allergies.score - x) == 0:
                Allergies.list.append(num_to_allergen[x])
                break
            elif (Allergies.score - x) < 0:
                pass
            else:
                Allergies.list.append(num_to_allergen[x])
                Allergies.score -= x
        Allergies.list.reverse()

    def is_allergic_to(self,allergen):
        return allergen in Allergies.list
