def is_leap_year(anno):
	if(anno % 400 == 0) or ((anno % 4 ==0) and (anno % 100 !=0)):
		return True
	return False
