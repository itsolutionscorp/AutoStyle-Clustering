translate = {
             1:'I', 4:'IV', 5:'V', 9:'IX',
             10:'X', 40:'XL', 50:'L', 90:'XC',
             100:'C', 400:'CD', 500:'D', 900:'CM',
             1000: 'M'
             }

def numeral(arabic):
	roman = ''
	for v in sorted(translate, reverse=True):
		while arabic >= v:
			roman += translate[v]
			arabic -= v
	return roman
