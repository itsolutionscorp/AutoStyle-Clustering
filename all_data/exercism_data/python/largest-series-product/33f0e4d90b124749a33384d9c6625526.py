class Series:
    def __init__(self,numbers):
        self.series = [ int(x) for x in numbers ]

    def slices(self,size):
        if size > len( self.series ):
            raise( ValueError( "Invalid slice length for this series: %s" % size ) )

        return [
            self.series[i:i+size] for i in range( len(self.series)-size+1 )
        ]

    def largest_product(self,size):
        if len(self.series) == 0 and size == 0 :
            return 1

        return max( [ 
            reduce( lambda x, y: x * y, numbers ) for numbers in self.slices(size) 
        ] )
