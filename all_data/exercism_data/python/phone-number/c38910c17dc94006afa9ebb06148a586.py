class Phone:
    def __init__(self, s):
        s = "".join(c for c in s if c.isnumeric())
        self.number = (s[-10:] if len(s) == 10 or len(s) == 11 and s[0] == "1"
                       else "0" * 10)

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "({}) {}-{}".format(
            self.number[:3], self.number[3:6], self.number[6:])
