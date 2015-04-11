
class Matrix():
    def __init__(self, string_representation):
        self.rows = [[int(val) for val in row.split()]
                     for row in string_representation.split('\n')]
        
    @property        
    def columns(self):
        return list(map(list, zip(*self.rows)))
