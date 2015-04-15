'''grains.py
	created 15 Oct 2014
	by @jestuber '''

def on_square(square):
	return 2**(square - 1)

def total_after(squares):
	return 2**squares - 1
