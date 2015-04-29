class Series(object):
    def __init__(self, data):
        self.data = [int(c) for c in data]
        
    def slices(self, num):
        if num < 1 or len(self.data) < num:
            raise ValueError("Invalid slice length for this series: %d" % num )

        return [ self.data[i:i+num] for i in xrange(len(self.data) - num + 1)]
        
