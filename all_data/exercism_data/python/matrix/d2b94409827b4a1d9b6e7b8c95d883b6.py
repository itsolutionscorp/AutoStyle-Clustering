class Matrix:
    def __init__(self, data):
        self.rows = []
        self.columns = []
        
        for row in data.split('\n'):
            self.rows.append(map(int, row.split()))
        
        for pos in range(len(self.rows[0])):
            self.columns.append([row[pos] for row in self.rows])
