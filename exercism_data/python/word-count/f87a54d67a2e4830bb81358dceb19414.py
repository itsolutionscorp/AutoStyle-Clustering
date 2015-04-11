import sets

class Phrase( object ):
    def __init__( self, s ):
        self._str = s or ""
        self._wordcount = None
    def word_count( self ):
        if self._wordcount:
            return self._wordcount

        words = self._str.split()
        wordsUnique = sets( words )
        self._wordcount = dict( zip( wordsUnique, [ words.count( x ) for x in wordsUnique ] ))
        return self._wordcount
