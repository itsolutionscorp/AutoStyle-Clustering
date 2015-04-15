class Matrix:
    
    def __init__(self,content):
        self.rows=[ [int(x) for x in row.split() ] for row in content.split("\n") ]
        self.columns=[list(x) for x in zip(*self.rows) ]

def main():
    """
    Test function, outputs the rows and columns of the matrix
    |1 2 3|
    |4 5 6|
    """
    
    test_matrix=Matrix("1 2 3\n4 5 6")
    print "rows=",test_matrix.rows
    print "columns=",test_matrix.columns


if __name__=="__main__":
  main()
