def on_square(number):
	return 2 ** (number - 1)

def total_after(squares):
	return sum(on_square(square + 1) for square in range(squares))
