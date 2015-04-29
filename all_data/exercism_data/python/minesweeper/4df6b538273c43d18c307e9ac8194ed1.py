

class Board():
    def __init__(self,inp):
        """Create a board from description (in the form of a list of strings)"""
        first, *middle, last = inp
        if any( len(line)!=len(first) or line[0]+line[-1]!="||" for line in middle):
            raise ValueError("Badly formatted input")
        if any( any(c not in "+-| *" for c in line) for line in inp ):
            raise ValueError("Invalid character in board descriptor")
        self.mines = [[ int(c=="*") for c in line[1:-1] ] for line in middle]
        self.l = len(self.mines)  # lines in the board
        self.c = len(self.mines[0])  # columns in the board
        self.minescount()


    def minescount(self):
        counttable = [[0]*self.c for _ in range(self.l)]
        for l in range(self.l):
            for c in range(self.c):
                counttable[l][c] = sum( self.mines[ll][cc]
                                        for ll in range(self.l)
                                        for cc in range(self.c)
                                        if  l in (ll,ll+1,ll-1) and c in (cc,cc+1,cc-1)
                                        )
        self.counttable = counttable
        return counttable

    def format(self):
        out = ""
        fl= "+"+"-"*self.c+"+"
        mat = [ ["*" if self.mines[l][c] else str(n) if n else " " for c,n in enumerate(line)] for l,line in enumerate(self.counttable) ]
        mat = ["|"+"".join(line)+"|" for line in mat]
        mat = [fl]+mat
        mat.append( fl )
        return mat



def board(inp):
    """must receive an board list: a list of strings"""
    return Board(inp).format()



