class Allergies:
    def __init__(self, score):
        self.score = score
        self.list = self._lookup()
    def is_allergic_to(self, item):
        return(item in self._lookup())
    def _lookup(self):
        binary = "{0:08b}".format(self.score)[-8:]
        l = []
        if binary[7] == '1':
            l.append("eggs")
        if binary[6] == '1':
            l.append("peanuts")
        if binary[5] == '1':
            l.append("shellfish")
        if binary[4] == '1':
            l.append("strawberries")
        if binary[3] == '1':
            l.append("tomatoes")
        if binary[2] == '1':
            l.append("chocolate")
        if binary[1] == '1':
            l.append("pollen")
        if binary[0] == '1':
            l.append("cats")
        return(l)
