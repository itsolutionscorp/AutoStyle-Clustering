class Series(object):
    def __init__(self, base):
        self.base = [int(x) for x in list(base)]

    def slices(self, length):
        sliceResults = []

        if length > len(self.base) or length < 1:
            raise ValueError("Invalid slice length for this series: %d" %length)

        for idx in range(0, len(self.base)+1 - length):
            sliceResults.append(self.base[idx:idx+length])

        return sliceResults
