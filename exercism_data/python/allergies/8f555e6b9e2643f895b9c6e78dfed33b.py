class Allergies():
    def __init__(self, score=0):
        self.score = score
        self.list = self.allergy_list(self.score)
    def allergy_list(self, score):
        allergy_item = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        allergy_score = [1, 2, 4, 8, 16, 32, 64, 128]
        if score > 256:
            temp = score - 256
        else:
            temp = score
        allergy_list = []
        for i in range(-1, -len(allergy_score) - 1, -1):
            if temp >= allergy_score[i]:
                temp -= allergy_score[i]
                allergy_list.insert(0,allergy_item[i])
        return allergy_list


    def is_allergic_to(self, item):
        if item in self.list:
            return True
