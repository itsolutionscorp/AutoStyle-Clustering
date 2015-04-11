class Binary(object):
    def __init__(self, s):
        n = 0
        for c in s:
            n *= 2
            if c == '1':
                n += 1
            elif c != '0':
                n = 0
                break
        self.n = n

    def __trunc__(self):
        return self.n
