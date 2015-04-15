'''grains.py
	created 15 Oct 2014
	by @jestuber '''

def on_square(square):
	if square == 1:
		return 1
	return 2*on_square(square-1)
def total_after(squares):
	if squares == 1:
		return 1
	return total_after(squares-1) + on_square(squares)
