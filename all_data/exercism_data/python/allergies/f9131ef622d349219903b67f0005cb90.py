allerglist = [('cats', 128),
              ('pollen', 64),
              ('chocolate', 32),
              ('tomatoes', 16),
              ('strawberries', 8),
              ('shellfish', 4),
              ('peanuts', 2),
              ('eggs', 1)]

class Allergies:
    def __init__(self, score):
        self.score = score
        self.score = score - int(self.score/256) * 256
        self.list = []
        if self.score == 255:
            self.list = [x for x, y in allerglist]
            self.list.reverse()
        elif self.score == 0:
            pass
        else:
            if self.score % 2 != 0:
                self.list.append('eggs')
                self.score -= 1
            for x, y in allerglist:
                if (self.score != 0) and (self.score % y == 0):
                    self.list.append(x)
                    self.score -= y

    def is_allergic_to(self, allergy):
        if allergy in self.list:
            return True
        else:
            return False
