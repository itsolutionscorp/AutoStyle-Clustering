def is_leap_year(year):
	m4, m100, m400 = [year % m == 0 for m in [4,100,400]]
	return m4 and (not m100 or m400)
