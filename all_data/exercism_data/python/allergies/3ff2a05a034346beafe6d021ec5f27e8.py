class Allergies:
    THINGS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes',
              'chocolate', 'pollen', 'cats']

    def __init__(self, allergies):
        self.allergies = allergies
        self.list = [Allergies.THINGS[i]
                        for i in range(len(Allergies.THINGS))
                            if 2**i & self.allergies]

    def is_allergic_to(self, thing):
        flag = Allergies.THINGS.index(thing)
        if self.allergies & 2**flag:
            return True

        return False
