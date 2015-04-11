'''exer queen_attack'''

EMPTY_CELL = '0'
WHITE_QUEEN = 'W'
BLACK_QUEEN = 'B'


def board(white_queen, black_queen):
    '''diagram a layout of the white and black queens on an 8x8 board'''
    if white_queen == black_queen:
        raise ValueError('Both can not occupy same cell')
    if off_the_board(white_queen):
        raise ValueError('White Queen placed outside of legal area')
    if off_the_board(black_queen):
        raise ValueError('Black Queen placed outside of legal area')

    brd = [list(EMPTY_CELL * 8) for _ in range(8)]

    brd[white_queen[0]][white_queen[1]] = WHITE_QUEEN
    brd[black_queen[0]][black_queen[1]] = BLACK_QUEEN

    return [''.join(row) for row in brd]

def off_the_board(piece):
    '''return True if piece located outside of legal play area'''
    return piece < (0, 0) or piece > (7, 7)

def can_attack(white_queen, black_queen):
    '''return True if queens can attack on horizontal, vertical or diagonal'''
    # use board to determine validity of board layout
    board(white_queen, black_queen)

    attack = False
    x_relative = abs(white_queen[0] - black_queen[0])
    y_relative = abs(white_queen[1] - black_queen[1])
    if x_relative == 0 or y_relative == 0:  # horizontal or vertical alignment
        attack = True
    if x_relative == y_relative:            # on a diagonal
        attack = True

    return attack
