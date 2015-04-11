class Matrix(object):
    def __init__(self,text):
        self.rows, self.columns = self.generate_matrix(text)

    def generate_matrix(self, text):
        string_matrix = [row.split() for row in text.split("\n")]
        rows = [[int(col) for col in row] for row in string_matrix]
        columns = [[row[i] for row in rows] for i in range(len(rows[0]))]
        return rows, columns

    
