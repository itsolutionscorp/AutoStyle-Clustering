legal_charsB = ['+','-']
legal_charsI = ['|','*',' ']

def board(what):
    matrix = [[i if i else 0 for i in j] for  j in what]
    ##testing for wrong input
    if len(max(matrix))!=len(min(matrix)):
        raise ValueError('The lengths are wrong')
    for cnt,row in enumerate(matrix):
        for col in row:
            if cnt == 0 or cnt == len(matrix)-1:
                if col not in legal_charsB:
                    raise ValueError('Inappropriate borders')
            else:
                if col not in legal_charsI:
                    raise ValueError('Inappropriate chars')
    ##
    ##every tile that is a * checks its neighbours
    ##if the neighbours are not int and not border they get the value 1
    ##if they are int they get the value += 1
    for cnty,i in enumerate(matrix):
        for cntx,j in enumerate(i):
            if j == '*':
                rec(matrix,cnty,cntx)
    return ["".join([str(i) for i in j])for j in matrix]

def rec(matrix,x,y):
    if x<len(matrix)-1:
        help(matrix,x+1,y)
        if y<len(matrix[0])-1:
            help(matrix,x+1,y+1)
    if x>1:
        help(matrix,x-1,y)
        if y>1:
            help(matrix,x-1,y-1)
    if y<len(matrix[0])-1:
        help(matrix,x,y+1)
        if x>1:
            help(matrix,x-1,y+1)
    if y>1:
        help(matrix,x,y-1)
        if x<len(matrix)-1:
            help(matrix,x+1,y-1)
    return matrix

def help(matrix,x,y):
    if matrix[x][y] == ' ':
        matrix[x][y] = 1
    elif isinstance(matrix[x][y],int):
        matrix[x][y] += 1
