class Mines(object):
    
    def __init__(self,board):
        
        self.check_valid_board(board)
        
        self.board = self.remove_borders(board)
        self.width = len(self.board[0])
        self.height = len(self.board)
        
        self.fill_positions()
        
    def check_valid_board(self,input_board):
        # Ensure all rows are of the same length
        if not all(len(line) == len(input_board[0]) for line in input_board[1:]):
        
            raise ValueError('All rows must be the same length.')
            
        # Ensure the borders are properly formatted
        cap = '+{0}+'.format('-' * (len(input_board[0]) - 2))       # +------+
        sides = '|{0}|'.format(' ' * (len(input_board[0]) - 2) )    # |      |
        if not input_board[0] == cap or not input_board[-1] == cap:
            raise ValueError('Must use valid border {0}.'.format(cap))
        if not all(line[0] == '|' and line[-1] == '|' for line in input_board[1:-1]):
            raise ValueError('Must use valid border {0}.'.format(sides))
        if not all(char in ' *' for line in input_board[1:-1] for char in line[1:-1]):
            raise ValueError("Invalid characters on board.  Must only be space or \'*\'.")
            
    def remove_borders(self,board):
        return [list(line[1:-1]) for line in board[1:-1]]
        
    def get_board(self):
        cap = '+{0}+'.format('-' * self.width)
        return [cap] + ['|{0}|'.format(''.join(line)) for line in self.board] + [cap]
        
    def count_sides(self,pos):
        y,x = pos
        left      = (y    , x - 1) if x > 0 else False
        right     = (y    , x + 1) if x < self.width - 1 else False
        up        = (y - 1, x    ) if y > 0 else False
        down      = (y + 1, x    ) if y < self.height - 1 else False
        upleft    = (y - 1, x - 1) if up and left else False
        upright   = (y - 1, x + 1) if up and right else False
        downleft  = (y + 1, x - 1) if down and left else False
        downright = (y + 1, x + 1) if down and right else False
        
        sides = [left, right, up, down, upleft, upright, downleft, downright]

        total = sum([1 for side in sides if side and self.board[side[0]][side[1]] == '*'])
        
        return ' ' if not total else str(total)

    def fill_positions(self):
        board = list(self.board)
        positions = {}
        for y, line in enumerate(board):
            for x, current in enumerate(list(line)):
                if not current == '*':
                    board[y][x] = self.count_sides((y,x))
                
        

def board(board):
    return Mines(board).get_board()

    
