class Series(object):
    def __init__(self, data):
        self.data = []
        for ch in data:
            self.data.append(int(ch))

    def next_slice(self, length):
        n = len(self.data)
        if length > n:
            raise ValueError, "Invalid slice length for this series: %d" % length

        for i in xrange(0, n):
            j = i + length
            if j > n: break
            yield self.data[i:j]
        return

    def slices(self, length):
        slice_list = []
        for slice in self.next_slice(length):
            slice_list.append(slice)
        return slice_list

    def largest_product(self, length):
        largest = 1
        for slice in self.next_slice(length):
            product = 1
            for val in slice:
                product *= val
            if product > largest:
                largest = product
        return largest
