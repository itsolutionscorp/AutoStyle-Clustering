class Matrix():

    def __init__( self, s ):
        rows_from_input = s.split('\n')
        self.rows    = [ [int(n) for n in row.split(' ')]
                         for row in rows_from_input ]
        self.columns = [ [row[ii] for row in self.rows]
                         for ii in range( len(self.rows[0]) ) ]

