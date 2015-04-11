'''def capitals(sample):
	for i in sample:
		if i.isalnum():
			if not i.isupper():
				return False
			else:
				return True
'''				
#all(map(lambda x: x.isupper(), string))
def hey(sample):
	if sample.isupper(): return u'Whoa, chill out!'
	elif sample.endswith('?'): return u"Sure."
	elif not sample.strip(): return u'Fine. Be that way!'
	return u"Whatever."
	
