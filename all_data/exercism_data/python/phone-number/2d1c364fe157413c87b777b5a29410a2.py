class Phone():

    def __init__( self, s ):
        self.number = ''.join( c for c in s if c.isdigit() )
        if len(str(self.number)) == 11 and str(self.number)[0] == '1':
            self.number = self.number[1:]
        if not len(str(self.number)) == 10:
            self.number = ''.join( '0' * 10 )

    def area_code( self ):
        return self.number[0:3]

    def pretty( self ):
        return ''.join([ '(', self.area_code(), ') ',
                          self.number[3:6], '-',
                          self.number[6:] ])
