class Matrix():

    def __init__(self, matrixString):

        self.rows = []
        self.columns = []

        # Split initial rows
        rowSplits = matrixString.split('\n')
        
        # Build Rows
        for index, individualRow in enumerate(rowSplits):
            self.rows.append(individualRow.split(' '))
            self.rows[index] = map(int, self.rows[index])

        # Build Columns
        for column in range (0, len(self.rows[0])):
            self.columns.append([row[column] for row in self.rows])
