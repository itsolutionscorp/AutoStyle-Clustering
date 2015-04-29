class Allergies(object):
    def __init__(self, score):
        allergy_score = bin(score)[2:].zfill(8)[-8:]
        items = ['cats', 'pollen', 'chocolate', 'tomatoes',
                 'strawberries', 'shellfish', 'peanuts', 'eggs']
        #self.list = []
        #for i, a_score in reversed(list(enumerate(allergy_score))):
        #    if a_score == '1':
        #        self.list.append(items[i])
        self.list = [items[i] for i, a_score in
                     reversed(list(enumerate(allergy_score)))
                     if a_score == '1']

    def is_allergic_to(self, item):
        return item in self.list

if __name__ == '__main__':
    allergies = Allergies(5)
    print allergies.is_allergic_to('eggs')
    print allergies.list
