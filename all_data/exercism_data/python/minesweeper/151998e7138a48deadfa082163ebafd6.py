import re

# Regex to check if the board is valid.
BOARDAO = re.compile("^\+{1}-+\+{1}$")
BOARDM = re.compile("^\|{1}[\s\*]+\|{1}$")

def board(board_in):
    board_list = [list(line) for line in board_in]
    for i in xrange(len(board_in)):
        if (i in [0, len(board_in)-1] and not BOARDAO.match(board_in[i])) or (i in range(1,len(board_in)-1) and not BOARDM.match(board_in[i])) or len(board_in[i]) != len(board_in[0]):
            raise ValueError("Invalid board.")
        if i in [0, len(board_in) - 1]:
            continue
        for j in xrange(1,len(board_list[i])-1):
            if board_list[i][j] == "*":
                for k in [-1,0,1]:
                    for l in [-1,0,1]:
                        if k==0 and l==0:
                            continue
                        try:
                            board_list[i+k][j+l] = str(int(board_list[i+k][j+l])+1)
                        except ValueError:
                            board_list[i+k][j+l] = "1" if board_list[i+k][j+l]==" " else board_list[i+k][j+l]
    return ["".join(line) for line in board_list]
