class Matrix:
    def __init__(self,text):
        self.rows = []

        for line in text.split("\n"):
            self.rows.append( [ int(num) for num in line.split(' ') if num.isdigit() ] )

        self.columns = []

        for c in xrange( len(self.rows[0] ) ):
            self.columns.append( [] )
            for r in xrange( len(self.rows) ):
                self.columns[c].append( self.rows[r][c] )
