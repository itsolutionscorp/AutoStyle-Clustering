class Matrix:
    def __init__(self, string):
        self.rows = [[int(number) for number in line.split()] 
                     for line in string.splitlines()]
        self.columns = [[self.rows[row_index][column_index]
                         for row_index in range(len(self.rows))]
                        for column_index in range(len(self.rows[0]))]
