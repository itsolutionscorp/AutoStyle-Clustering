class Matrix(object):


    def __init__(self, string_matrix):
        self.rows = []
        rows = string_matrix.split("\n")
        self.columns = [ [] for r in rows ]
        for r in rows:
            row = map(int, r.split(" "))
            self.rows.append(row)
            self.columns.append([])
            for i, val in enumerate(row):
                self.columns[i].append(val)
