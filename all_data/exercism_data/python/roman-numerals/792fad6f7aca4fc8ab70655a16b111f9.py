hund = {0:'',1:'C',2:'CC',3:'CCC',4:'CD',5:'D',6:'DC',7:'DCC',8:'DCCC',9:'CM'}
dec = {0:'',1:'X',2:'XX',3:'XXX',4:'XL',5:'L',6:'LX',7:'LXX',8:'LXXX',9:'XC'}
unit = {0:'',1:'I',2:'II',3:'III',4:'IV',5:'V',6:'VI',7:'VII',8:'VIII',9:'IX'}

def numeral(number):
	roman = ''
	stNum = str(number)
	l = len(stNum)
	
	if len(stNum) == 4:
		for n in range(0,int(stNum[0])):
			roman+='M'
		stNum=stNum[1:]
		
	if len(stNum) == 3:
		roman+=hund[int(stNum[0])]
		stNum=stNum[1:]
	
	if len(stNum) == 2:
		roman+=dec[int(stNum[0])]
		stNum=stNum[1:]
		
	if len(stNum) == 1:
		roman+=unit[int(stNum[0])]
		stNum=stNum[1:]
		
	return roman
