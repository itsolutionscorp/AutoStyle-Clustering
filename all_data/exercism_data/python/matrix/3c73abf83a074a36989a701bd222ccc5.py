class Matrix(object):
    
    def __init__(self, num_string):
    
        self.num_string = num_string
        self.rows = self.create_rows(num_string)
        self.columns = self.create_columns(num_string)        
    
    def create_rows(self, num_string):
        rows = [x.split(' ') for x in num_string.splitlines()]
        return [map(int, i) for i in rows]           

    def create_columns(self, num_string):

        num_list = [int(i) for i in self.num_string.split() if i.isalnum()]
        column_size = len(self.rows[0])
        return [num_list[i::column_size] for i in xrange(column_size)]
