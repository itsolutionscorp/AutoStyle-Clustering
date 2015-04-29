def on_square(square_num):
	return 2**(square_num - 1)

def total_after(square_num):
	if square_num == 1:
		return 1
	else:
		return on_square(square_num) + total_after(square_num-1)

print total_after(16)
