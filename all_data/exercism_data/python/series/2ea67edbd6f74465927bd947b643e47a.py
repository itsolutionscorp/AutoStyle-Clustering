class Series(object):
    def __init__(self, s):
        self.value = [int(i) for i in s]

    def slices(self, n):
        val = self.value
        if n == 0 or n > len(val):
            raise ValueError("Invalid slice length for this series: {}".format(n))
        return [val[i:i+n] for i in range(len(val)-n+1)]
