class Series:
    def __init__(self, digits):
        self.series = [int(c) for c in digits]

    def slices(self, n):
        if n < 1 or n > len(self.series):
            raise ValueError("Invalid slice length for this series: %d" % n)
        return [self.series[i:i+n] for i in range(len(self.series)-n+1)]
