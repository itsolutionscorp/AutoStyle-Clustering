class Allergies():
    def __init__(self,score):
        ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries',
            'tomatoes', 'chocolate', 'pollen', 'cats']
        self.list = [item for (shift, item) in enumerate(ALLERGENS)
                     if score >> shift & 1]
            # shamelessly taken from @mariazverina 's solution

    def is_allergic_to(self,text):
        return text in self.list
