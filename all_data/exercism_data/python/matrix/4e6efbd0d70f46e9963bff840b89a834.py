class Matrix(object):

    def __init__(self, text):
        self.rows = [[int(word) for word in line.split()] for line in text.splitlines()]
        num_cols = len(self.rows[0])
        self.columns = [[row[col] for row in self.rows] for col in range(num_cols)]
