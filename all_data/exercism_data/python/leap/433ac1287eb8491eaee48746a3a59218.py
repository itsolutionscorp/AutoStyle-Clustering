# Check if input is leap year
import sys

# Actually check if year is divisible by 4 but not 100 unless divisible by 400
def is_leap_year(year):
	if year % 4 == 0:
		if year % 400 ==0:
			return True
		elif year % 100 == 0:
			return False
		return True
	return False

# First check if there is an argument already, if not assk for the year
def main():
	if len(sys.argv) > 1:
		inputYear = sys.argv[1] 
	else: 
		inputYear = raw_input('Which year do you want to check? ')
	if is_leap_year(int(inputYear)) == True:
		print('That is a leap year')
		sys.exit(0)
	else: 
		print ('That is not a leap year')
		sys.exit(1)

version = "1.0"

if __name__ == '__main__':
	main()
