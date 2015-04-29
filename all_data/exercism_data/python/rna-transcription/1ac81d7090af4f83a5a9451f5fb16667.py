class DNA( object ):
    def __init__( self, string ):
        self.__rna__ = None
        self.__str__ = string

    def to_rna( self ):
        if not self.__rna__:
            self.__rna__ = self.__str__.replace( "T", "U" )
        return self.__rna__
