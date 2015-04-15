from re import findall

# extracts ten digits from the given string, ignoring all non-digit characters.
# drops a leading 1 from 11-digit strings
# returns "0000000000" if there are no valid ten digits.
def _extract_ten_digits(string):
	partial=findall("\\d",string)
	if (len(partial)!=10 and len(partial)!=11) or (len(partial)==11 and partial[0]!='1'):
		return "0000000000"
	if len(partial)==11:
		return "".join(partial[1:])
	return "".join(partial)

# represents a phone number
class Phone(object):
	def __init__(self,string):
		self._number=_extract_ten_digits(string)

	@property
	def number(self):
		return self._number

	def area_code(self):
		return self._number[:3]

	def pretty(self):
		return "("+self._number[:3]+") "+self._number[3:6]+"-"+self._number[6:]
