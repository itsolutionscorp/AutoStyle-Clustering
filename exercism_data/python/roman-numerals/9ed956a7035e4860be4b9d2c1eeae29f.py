def numeral(arabic):
		arastr=str(arabic)
		count=len(arastr)
		initcount=count
		rom =''
		if count ==4:
			rom +='M'*int(arastr[0])
			count -=1
		if count ==3:
			divide = int(arastr[initcount-count]) % 5
			if divide == int(arastr[initcount-count]):
				if divide ==4:
					rom+='CD'
				else:
					rom+='C'*int(divide)
			else:
				if int(arastr[initcount-count])==9:
					rom+='CM'
				else:
					rom+='D'+'C'*int(divide)
			count-=1
		if count ==2:
			divide = int(arastr[initcount-count]) % 5
			if divide == int(arastr[initcount-count]):
				if divide ==4:
					rom+='XL'
				else:
					rom+='X'*int(divide)
			else:
				if int(arastr[initcount-count])==9:
					rom+='XC'
				else:
					rom+='L'+'X'*int(divide)
			count-=1
		if count ==1:
			print len(arastr)
			divide = int(arastr[initcount-count]) % 5
			if divide == int(arastr[initcount-count]):
				if divide ==4:
					rom+='IV'
				else:
					rom+='I'*int(divide)
			else:
				if int(arastr[initcount-count])==9:
					rom+='IX'
				else:
					rom+='V'+'I'*int(divide)
			count-=1
		return rom
	
