class Matrix(object):
    """docstring for Matrix"""
    def __init__(self, matrix_string):
        super(Matrix, self).__init__()
        elements = [int(character) for character in matrix_string.split()]
        nrows = matrix_string.count("\n")+1
        ncols = len(elements)/nrows
        Matrix.rows = [elements[x*ncols:x*ncols+ncols] for x in range(0,nrows)]
        Matrix.columns = [elements[x:-1:ncols] for x in range(0,ncols)]




if __name__ == '__main__':
    matrix = Matrix("1 2 3\n4 5 6\n7 8 9\n 8 7 6")
    print matrix.columns
    
        
