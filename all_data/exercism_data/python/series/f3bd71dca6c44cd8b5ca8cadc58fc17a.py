class Series(object):

    def __init__(self, input_string):
        self.length = len(input_string)
        try:
            self.lst = [int(c) for c in input_string]
        except:
            raise ValueError("Please input a string that only contains numbers")

    def slices(self, n):

        if n > self.length or n <= 0:
            raise ValueError("Invalid slice length for this series: %d" % n)

        return [ self.lst[i:i+n] for i in range(self.length - n + 1) ]
