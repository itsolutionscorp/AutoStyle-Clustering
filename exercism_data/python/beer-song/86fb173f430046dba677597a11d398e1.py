str1 = "{t} bottles of beer on the wall, {t} bottles of beer.\n"
str2 = "Take one down and pass it around, {} bottles of beer on the wall.\n"

str_pre_end = "1 bottle of beer on the wall, 1 bottle of beer.\n" + "Take it down and pass it around, no more bottles of beer on the wall.\n"

endstr = "No more bottles of beer on the wall, no more bottles of beer.\n" + "Go to the store and buy some more, 99 bottles of beer on the wall.\n";

def verse(times):
	result = '' 
	#the end of the song
	if (times == 0):
		result += endstr
		return result
	#pre end
	elif (times == 1):
		result += str_pre_end
		return result
	else: 
		if (times - 1 == 1):
			temp_str = str2
			temp_str = temp_str.replace('bottles', 'bottle')
			result += str1.format(t=times)
			result += temp_str.format(times - 1)
			return result
		else:
			result += str1.format(t=times);
			result += str2.format(times - 1)
			return result

def song(end, start=0):
	result = ''
	times = range(start, end+1)
	for n in times[::-1]: 
		result += verse(int(n))
		result += "\n"
	return result 
