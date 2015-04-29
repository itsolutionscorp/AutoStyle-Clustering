def add_gigasecond(birthday):
	import math
	from datetime import date, timedelta
	
	gigasecond = 10 ** 9
	gs_date = birthday + timedelta(seconds=gigasecond)

	return gs_date
