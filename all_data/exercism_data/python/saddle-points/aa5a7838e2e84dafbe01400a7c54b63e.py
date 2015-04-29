#reusing this class from a previous assignment
class Matrix:

    def __init__(self,matrix):
        rows = []
        self.matrix = matrix
        for i in self.matrix:
            rows.append([int(j) for j in i])
        columns = [[] for i in range(len(rows[0]))]
        for cnt in range(len(rows[0])):
              for j in range(len(rows)):
                  columns[cnt].append(rows[j][cnt])
        self.rows = rows
        self.columns = columns

def saddle_points(inp):
    if not inp:
        return set()
    if (max(map(len,inp)))!=(min(map(len,inp))):
        raise ValueError
    stats = Matrix(inp)
    ls = []
    for cntr,row in enumerate(stats.rows):
        for cntc,elem in enumerate(row):

            if elem==max(row) and elem==min(stats.columns[cntc]):
                ls.append((cntr,cntc))
    return set(ls)
