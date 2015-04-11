class Matrix:
    def __init__(self, numberstring):
        lines = numberstring.split("\n")

        self.rows = [line.split() for line in lines]
        self.columns = [[] for x in range(len(self.rows[0]))]

        for x in range(len(self.columns)):
            for row in self.rows:
                row[x] = int(row[x])
                self.columns[x].append(row[x])
        
        
    
