'''
Calculate the number of grains of wheat on a chessboard given that the number on each square doubles
'''
def on_square(squarenum):
	return 2**(squarenum-1)

def total_after(squarenum):
	return 2**squarenum - 1
	
