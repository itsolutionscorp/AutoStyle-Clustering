def board(input_board):

    output_board = []
    numrows = len(input_board)
    if numrows > 0:
        rowlen = len(input_board[0])

    for n, row in enumerate(input_board):

        if len(row) != rowlen:
            raise ValueError('Invalid board: all rows must be the same length')

        output_row = list(row)

        if n > 0 and n < numrows - 1:           
            for m, square in enumerate(output_row):
                if m > 0 and m < len(output_row) - 1:
                    if square == ' ':
                        output_row[m] = count_neighbours(input_board, n, m)
                    elif square != '*':
                        raise ValueError('Invalid character ' + square)
                elif square != '|':
                    raise ValueError('Invalid border')
        
        elif row.count('+') + row.count('-') != rowlen:
            raise ValueError('Invalid border')

        output_board.append(''.join(output_row))

    return output_board


def count_neighbours(board, row, col):
    neighbours = []
    for i in range(row-1, row+2):
        for j in range(col-1, col+2):
            neighbours.append(board[i][j])
    num_neighbours = neighbours.count('*')
    if num_neighbours > 0:
        return str(num_neighbours)
    else:
        return ' '
