from collections import Counter
import re
def word_count(phrase):
    plist=re.findall('([\w]+)',phrase)
    for i in range(len(plist)):
	plist[i]=str.lower(plist[i])
    return dict(Counter(plist).most_common())
