class Series(object):

    def __init__(self, input_string):
        self.num = input_string
        self.length = len(self.num)
        try:
            self.lst = [int(c) for c in self.num]
        except:
            self.lst = []
            
    def slices(self, n):

        if n > self.length or n <= 0:
            raise ValueError("Invalid slice length for this series: %d" % n)

        ret = []
        for i in range(self.length - n + 1):
            each_slice = self.lst[i:i+n]
            ret.append(each_slice)

        return ret
