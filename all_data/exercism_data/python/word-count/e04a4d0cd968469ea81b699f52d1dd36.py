from collections import*
from re import*
class Phrase(str):word_count=lambda s:Counter(findall("\w+",s.lower()))
