class Allergies(object):

    def __init__(self, score):
        self.score = score
        self.list = Allergies.list_allergies(self)

    def list_allergies(self):
        allergy_list = []
        counter = self.score

        if counter >= 128:
            allergy_list.append('cats')
            counter -= 128
        if counter >= 64:
            allergy_list.append('pollen')
            counter -= 64
        if counter >= 32:
            allergy_list.append('chocolate')
            counter -= 32
        if counter >= 16:
            allergy_list.append('tomatoes')
            counter -= 16
        if counter >= 8:
            allergy_list.append('strawberries')
            counter -= 8
        if counter >= 4:
            allergy_list.append('shellfish')
            counter -= 4
        if counter >= 2:
            allergy_list.append('peanuts')
            counter -= 2
        if counter >= 1:
            allergy_list.append('eggs')
            counter -= 1

        allergy_list.reverse()
        return allergy_list

    def is_allergic_to(self, food):
        return food in self.list
