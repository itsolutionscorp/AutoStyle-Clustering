def total_after(sNum):
	if sNum == 1:
		return 1
	else:
		return on_square(sNum)+total_after(sNum-1)
		
def on_square(sNum):
	return 2**(sNum-1)
