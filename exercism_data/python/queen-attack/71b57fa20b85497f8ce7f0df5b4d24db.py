def can_attack(tapa1,tapa2):
    checker(tapa1,tapa2)
    xdif = abs(tapa1[0]-tapa2[0])
    ydif = abs(tapa1[1]-tapa2[1])
    return xdif == ydif or xdif == 0 or ydif == 0

def board(tapa1,tapa2):
    checker(tapa1,tapa2)
    matrix = ([['_' for i in range(8)] for i in range(8)])
    for cntx,i in enumerate(matrix):
        for cnty,j in enumerate(i):
            if (cntx,cnty) == tapa1:
                matrix[cntx][cnty] = ('W')
            elif (cntx,cnty) == tapa2:
                matrix[cntx][cnty] = ('B')
    return ["".join([i for i in j]) for j in matrix]

def checker(tapa1,tapa2):
    if tapa1==tapa2:
        raise ValueError('Same spot different Queen')
    if tapa1[1] > 7 or tapa2[1] > 7:
        raise ValueError('Out of Borders')
    elif tapa1[0]<0 or tapa2[0] < 0:
        raise ValueError('Out of Borders')
