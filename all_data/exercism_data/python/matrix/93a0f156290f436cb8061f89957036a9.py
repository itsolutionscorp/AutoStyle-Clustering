class Matrix():

    def __init__(self, matrix_string):
        """Instantiates a matrix with where
        rows and columns can be addressed"""
        self.rows = []
        self.columns = []

        # First split into strings representing rows
        row_strings = matrix_string.split('\n')

        # Split rows apart and convert to integers
        for row_string in row_strings:
            new_row = []
            row_elements = row_string.strip().split(" ")
            for row_element in row_elements:
                new_row.append(int(row_element))
            self.rows.append(new_row)

        # Use self.rows to find columns
        for i in range(0, self.rows[0].__len__()):
            new_column = []

            for row in self.rows:
                new_column.append(row[i])

            self.columns.append(new_column)
