__author__ = 'angelo'


class Series:

    def __init__(self, s):
        self.s = s

    def slices(self, l):
        if l < 1 or l > len(self.s):
            raise ValueError("Invalid slice length for this series: " + str(l))
        slice_list = []
        start = 0
        end = start + l
        while end <= len(self.s):
            slice_list.append([int(x) for x in self.s[start:end]])
            start += 1
            end += 1
        return slice_list
