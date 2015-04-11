class Series(object):
    def __init__(self, data):
        self.data = []
        for ch in data:
            self.data.append(int(ch))

    def slices(self, length):
        n = len(self.data)
        if length < 1 or length > n:
            raise ValueError, "Invalid slice length for this series: %d" % length

        slice_list = []
        for i in xrange(0, n):
            j = i + length
            if j > n: break
            slice_list.append(self.data[i:j])
        return slice_list
