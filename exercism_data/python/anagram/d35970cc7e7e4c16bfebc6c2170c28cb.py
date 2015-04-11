#anagram
import collections
from collections import Counter
#import string

def detect_anagrams(source, lists):
	result=[]
	source=source.lower()
	for ele in lists:
		if collections.Counter(source) == collections.Counter(ele.lower()) and source!= ele.lower():
			result.append(ele)

	return result
