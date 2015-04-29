class Chess_Board(object):
    
    def __init__(self,white,black,width = 8, height = 8):
        self.w = white
        self.b = black
        self.width = width
        self.height = height
        self.board = []
        
        self._valid_positions()
        self._create_board()
        
    def _create_board(self):
        queens = {self.w:'W', self.b:'B'}
        for row in xrange(self.height):
            line = ''
            for col in xrange(self.width):
                if (row,col) in [self.w,self.b]:
                    line += queens[(row,col)]
                else:
                    line += '_'
            self.board.append(line)
            
    def _valid_positions(self):
        if self.w == self.b:
            raise ValueError('Pieces must be on different squares.')
            
        wy,wx,width = self.w[0],self.w[1],self.width
        by,bx,height = self.b[0],self.b[1],self.height
        
        in_range = lambda x,valid_range: x in range(valid_range)
        if not in_range(wy,width) or not in_range(by,width) \
                    or not in_range(wx,height) or not in_range(bx,height):
            raise ValueError('Positions must be within the boundries of the board.')
            
    def can_attack(self):
        return self.w[0] == self.b[0] or self.w[1] == self.b[1] or \
                abs(self.w[0] - self.b[0]) == abs(self.w[1] - self.b[1])
        
def board(white,black):
    return Chess_Board(white,black).board
    
def can_attack(white,black):
    return Chess_Board(white,black).can_attack()
