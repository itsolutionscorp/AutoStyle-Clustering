class Allergies():
    def __init__(self,score):
        binary = bin(score)[2:]  # this is a string, not a number
        ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries',
            'tomatoes', 'chocolate', 'pollen', 'cats']
        self.list = []

        for a in ALLERGENS:
            if binary[-1] == "1":  #test least significant bit
                self.list.append(a)
            binary = binary[:-1]
            if binary == "":  # no more significant bits
                break

##        that was much more elegant, but the test suite can't bear with different ordered lists.
##        allergies = dict(zip(ALLERGENS,binary[::-1]))
##        self.list = [a for a in allergies if allergies[a] == '1']

    def is_allergic_to(self,text):
        if text in self.list:
            return True
        else:
            return False
