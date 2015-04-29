class Series:
    def __init__(self,series):
        self.series = [ int(x) for x in series ]

    def slices(self,n):
        if n > len(self.series) or n <= 0:
            raise( ValueError( "Invalid slice length for this series: %s" % n ) )

        return [ self.series[i:i+n] for i in range(len(self.series)-n+1) ]
