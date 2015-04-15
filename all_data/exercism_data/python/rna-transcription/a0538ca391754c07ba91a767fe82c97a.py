class DNA( str ):
    def __init__( self, string ):
        super( DNA, self ).__init__()
    def to_rna( self ):
        return self.replace( "T","U" )
