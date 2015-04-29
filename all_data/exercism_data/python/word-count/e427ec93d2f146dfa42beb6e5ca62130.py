import re

def word_count(p):
	res = {}

	for w in p.split(" "):
		m = re.match("[A-Za-z0-9]+", w) # Return the MatchObject or None if there's no match

		if not m:	# If there was no match, continue
			continue

		ms = m.group(0) # The matching string
		ms = ms.lower() # Consider words equal regardless of case

		if ms not in res:
			res[ms] = 1
		else:
			res[ms] = res[ms] + 1

	return res

if __name__ == "__main__":
	print word_count('go Go GO')
