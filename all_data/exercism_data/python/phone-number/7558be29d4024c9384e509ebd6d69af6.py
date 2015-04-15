from string import digits

class Phone:
    def __init__(self, numberstring):
        self.number = "".join(d
                              for d in numberstring
                              if d.isdigit())
        if len(self.number) != 10:
            if self.number[0] is not "1" or 11 > len(self.number) < 10:
                self.number = "0"*10
            self.number = self.number[-10:]

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        a, pre, post = self.area_code(), self.number[3:6], self.number[6:]
        return "({0}) {1}-{2}".format(a, pre, post)
