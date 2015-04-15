import sys

def word_count(sentance):
	wordcount = {}
	for word in sentance.split():
		if word in wordcount:
			wordcount[word] += 1
		else:
			wordcount[word] = 1
	return wordcount

def main():
	# If there is a sentance already given in the command line, use it
	if len(sys.argv) > 1:
		sentancearr = sys.argv
		script = sentancearr.pop(0)
		# The string at the beginning of join is the seperator that will be used to join the array together
		sentance = ' '.join(sentancearr)
	else:
		sentance = raw_input('Please enter a sentance you want to check: ')
	results = word_count(sentance)
	for word in results:
		print word, results[word]

version = "1.0"

if __name__ == '__main__':
        main()
