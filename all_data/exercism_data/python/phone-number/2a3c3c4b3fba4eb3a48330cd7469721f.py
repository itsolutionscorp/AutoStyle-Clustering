class Phone:
    def __init__(self, s):
        self.number = '0000000000'

        n = ''
        for c in s:
            if c.isdigit():
                n += c

        if len(n) == 10:
            self.number = n
        if len(n) == 11 and n[0] == '1':
            self.number = ''.join(n[1:])

    def area_code(self):
        return ''.join(self.number[:3])

    def pretty(self):
        s = '(' + ''.join(self.number[:3]) + ') '
        s += ''.join(self.number[3:6]) + '-'
        s += ''.join(self.number[6:])
        return s
