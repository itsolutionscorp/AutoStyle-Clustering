class Matrix(object):
    def __init__(self,text):
        self.rows = [[int(char) for char in line.split()]
                                for line in text.split("\n")]
        self.columns = [[row[i] for row in self.rows]
                                for i in range(len(self.rows[0]))]
    
