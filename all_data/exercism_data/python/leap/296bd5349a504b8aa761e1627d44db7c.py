

def is_leap_year(date):
	four = 'fail'
	hun = 'fail'
	fourhun = 'fail'
	fourhun = 'fail'
	if date % 4 == 0:
		four = 'pass'
	if date % 100 == 0:
		hun = 'pass'
	if date % 400 == 0:
		fourhun == 'pass'
	if four == 'pass' and hun == 'fail':
		return True
	elif four == 'pass' and hun == 'pass' and fourhun == 'pass':
		return True
	else:
		return False
