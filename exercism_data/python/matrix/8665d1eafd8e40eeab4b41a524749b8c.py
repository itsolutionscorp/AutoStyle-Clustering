class Matrix:

    def __init__(self, matstr):

        self.rows = []

        # Extract rows
        for row in matstr.split('\n'):
            self.rows.append([int(x) for x in row.split()])

        self.columns = []

        rowlen = len(self.rows[0])
        
        # Check that the matrix is valid
        for row in self.rows:
            if len(row) != rowlen:
                raise ValueError("All rows of the matrix must have the same length")
            
        # Extract columns
        for i in range(rowlen):
            self.columns.append([row[i] for row in self.rows])
