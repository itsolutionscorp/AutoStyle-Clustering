TWELVE_DAYS_SOURCE = 'twelve_days.txt'
with open(TWELVE_DAYS_SOURCE, 'r') as input_file:
    TWELVE_DAYS_VERSES = input_file.read().split('\n\n')

def sing():
	return verses(1, 12)

def verse(number):
	return TWELVE_DAYS_VERSES[number - 1] + '\n'

def verses(start, end):
	return '\n\n'.join(TWELVE_DAYS_VERSES[start-1:end]) + '\n\n'
