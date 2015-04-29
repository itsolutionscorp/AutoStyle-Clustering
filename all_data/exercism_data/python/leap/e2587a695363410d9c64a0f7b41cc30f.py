def is_leap_year(year):
	def divisible_by_four(num):
		return int(num)%4 == 0
	def exceptions(num):
		return int(num)%200 == 0 or int(num)%400 == 0
	if int(year)%100 !=0:
		return divisible_by_four(year)
	else:
		return exceptions(year)
print is_leap_year("1996")
