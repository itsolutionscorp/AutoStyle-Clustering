class Matrix(object):
    
    def __init__(self,matrix):
        """Matrix can take string and list type in the following formats:
           string:  '9 8 7\n5 3 2\n6 6 7'
           list  :  [[9, 8, 7], [5, 3, 2], [6, 6, 7]]
        """
        self.rows = self.getRows(matrix) if matrix else []
        self.num_rows = len(self.rows) if matrix else 0
        self.num_cols = len(self.rows[0]) if matrix else 0
        self.columns = self.getColumns() if matrix else []

    def getRows(self, matrix):
        
        if isinstance(matrix,str):
            matrix = [map(int,x.split()) for x in matrix.split('\n')]
            
        # Raise ValueError if all rows are not equal in length
        if not all([len(row) == len(matrix[0]) for row in matrix]):
            raise ValueError('Rows must be of equal length!')
            
        return matrix
        
    def getColumns(self):
        """Flatten matrix then combine columns into list elements"""
        flat_rows = [item for row in self.rows for item in row]
        return [flat_rows[column::self.num_cols] for column in xrange(self.num_cols)]
        
    def get_saddle_points(self):
        return set([(row_num,col_num) for row_num, row in enumerate(self.rows) \
                    for col_num in range(self.num_cols) if row[col_num] == max(row) \
                        and row[col_num] == min(self.columns[col_num])])
    

def saddle_points(inp):
    return Matrix(inp).get_saddle_points()
