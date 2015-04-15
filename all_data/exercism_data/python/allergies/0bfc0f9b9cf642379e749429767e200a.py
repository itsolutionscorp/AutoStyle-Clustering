class Allergies(object):

    def __init__(self,allergy_score):
        num_to_allergen = ['eggs','peanuts','shellfish','strawberries',
                           'tomatoes','chocolate','pollen','cats']
        Allergies.score = allergy_score % 256
        Allergies.list = []
        index = 8

        while Allergies.score > 0:
            index -= 1
            if not (Allergies.score - (1 << index)) < 0:
                Allergies.list.append(num_to_allergen[index])
                Allergies.score -= (1 << index)

        Allergies.list.reverse()

    def is_allergic_to(self,allergen):
        return allergen in Allergies.list
