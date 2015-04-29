from collections import defaultdict

class Matrix:    
    def __init__(self, matrix):
        self.rows = [[int(x) for x in row.split(' ') if x.strip() != ''] for row in matrix.split('\n')]        
        self.columns = defaultdict(list)
        for i in range(len(self.rows)):
            for j in range(len(self.rows[i])):
                self.columns[j].append(self.rows[i][j])                
