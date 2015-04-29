class Matrix:

    def __init__(self,matrix):
        rows = []
        self.matrix = matrix.split('\n')
        for i in self.matrix:
            rows.append([int(j) for j in i.split()])
        columns = [[] for i in range(len(rows[0]))]
        for cnt in range(len(rows[0])):
              for j in range(len(rows)):
                  columns[cnt].append(rows[j][cnt])
        self.rows = rows
        self.columns = columns
