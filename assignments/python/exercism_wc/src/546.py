import re
from collections import Counter
def word_count(text):
     return Counter( s.lower() for s in re.split(r'\W+',text) if s )
