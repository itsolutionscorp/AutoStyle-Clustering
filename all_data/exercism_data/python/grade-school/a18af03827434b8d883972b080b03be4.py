class School(object):

    def __init__( self, *args ):
        self._db = {}

    def getdb( self ):
        return self._db

    def add( self, name, grade ):
        if grade in self._db:
            self._db[ grade ].add( name )
        else:
            self._db[ grade ] = { name }

    def grade( self, grade ):
        if grade in self._db:
            return self._db[ grade ]
        return set()

    def sort( self ):
        sorted_db = { grade:tuple(sorted(self._db[grade]))
                       for grade in self._db }
        return sorted_db

    db = property( getdb )
