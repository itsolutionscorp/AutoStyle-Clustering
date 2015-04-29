class Matrix():
    """given a string representing a matrix of numbers,
    return the rows or columns of that matrix"""
    def __init__(self, arg):
        rows = []
        for row in arg.split('\n'):
            row = row.split()
            row = [int(a) for a in row]
            rows.append(row)
        self.rows = rows

        columns = []
        for i in range(0,len(rows[0])):
            print i
            column = []
            for row in rows:
                column.append(row[i])
            columns.append(column)
        self.columns = columns


    def rows(self):
        return self.rows


    def columns(self):
        return self.columns
