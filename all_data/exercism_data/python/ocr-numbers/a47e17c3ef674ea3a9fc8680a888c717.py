ROW = 4
COL = 3

all = ['    _  _     _  _  _  _  _  _ ',
       '  | _| _||_||_ |_   ||_||_|| |',
       '  ||_  _|  | _||_|  ||_| _||_|', 
       '                              ']

ocr_list = [[all[i][COL*j:COL*(j+1)]
	     for i in range(ROW)]
	     for j in range(10)]

ocr_list = [ocr_list[-1]] + ocr_list[:9]


def number(ocr):
	if len(ocr) != ROW or len(ocr[0])%COL or any(len(r) != len(ocr[0]) for r in ocr):
		raise ValueError('Wrong grid size.')
	try:
		return str(ocr_list.index(ocr))
	except ValueError:
		return '?'
	
def grid(n):
	return ocr_list[int(n)]
