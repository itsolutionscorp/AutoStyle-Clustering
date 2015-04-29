import re
def word_count(instr):
	words = re.split('[^a-z\d]', instr.lower())
	keys = set(words)
	ret = {}
	for key in filter(None, keys):
		ret[key] = words.count(key)
	return ret
