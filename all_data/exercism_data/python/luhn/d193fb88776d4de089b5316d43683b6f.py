class Luhn():

    def __init__( self, n ):
        self.n = n

    def addends( self ):
        lenN = len( str(self.n) )
        return [ self.luhn_dig( int(str(self.n)[ ii ]), lenN - ii - 1)
                 for ii in range( 0, lenN ) ]

    def doubled_dig( self, d ):
        return { True : 2 * d,
                 False: 2 * d - 9 }[ d < 5 ]

    def luhn_dig( self, d, pos ):
        return { 0: d,
                 1: self.doubled_dig( d ) }[ pos%2 ]

    def checksum( self ):
        return sum( self.addends() ) % 10

    def is_valid( self ):
        return self.checksum() == 0

    def create( n ):
        l = Luhn( 10*n )
        return 10*n + (-l.checksum() % 10)
