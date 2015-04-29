class DNA( object ):
    def __init__( self, string ):
        self._rna = None
        self._str = string

    def to_rna( self ):
        if not self._rna:
            self._rna = self._str.replace( "T", "U" )
        return self._rna
