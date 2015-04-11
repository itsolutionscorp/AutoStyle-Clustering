class Series(object):
    def __init__(self, data):
        self.data = [int(c) for c in data]
        self.length = len(self.data)
        
    def slices(self, num):
        if num > self.length:
            raise ValueError("Invalid slice length for this series: %d" % num )
        if num < 1:
            raise ValueError("Invalid slice length for this series: 0")

        return [ self.data[i:i+num] for i in xrange(self.length - num + 1)]
        
