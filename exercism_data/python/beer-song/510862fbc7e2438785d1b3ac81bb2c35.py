def verse(n):
	return (number(n) + bottles(n) + ' of beer on the wall, ' +
                number(n).lower() + bottles(n) + ' of beer.\n' +
                pass_around(n) + number(n-1).lower() +
                bottles(n-1) + ' of beer on the wall.\n')
	
def song(n, m = 0):
	song = ''
	for i in range(n, m-1, -1):
		song += verse(i) + '\n'
	return song 
	
def number(n):
	if n == 0:
		return 'No more'
	return str(n % 100)
	
def bottles(n):
	if n == 1:
		return ' bottle'
	return ' bottles'
	
def pass_around(n):
	if n == 0:
		return 'Go to the store and buy some more, '
	one = 'it' if n == 1 else 'one'
	return 'Take ' + one + ' down and pass it around, '
