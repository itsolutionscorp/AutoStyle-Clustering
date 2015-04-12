class Matrix(object):
    def __init__(self,text):
        self.rows, self.columns = self.generate_matrix(text)

    def generate_matrix(self, text):
        rows_as_strings = text.split("\n")
        string_matrix = [row.split(" ") for row in rows_as_strings]
        rows = [[int(col) for col in row if bool(col)] for row in string_matrix]
        columns = [[row[i] for row in rows] for i in range(len(rows[0]))]
        return rows, columns

    