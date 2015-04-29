class Allergies:

    sources = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
    allergic_to = dict()
    list = sources

    def __init__(self, score):

        self.allergic_to = dict(zip(self.sources, [score & (1 << n) for n, source in enumerate(self.sources)]))
        self.list = [s for s in self.sources if self.allergic_to[s]]


    def is_allergic_to(self, source):

        return self.allergic_to[source]
