def numeral(n):
	M,remM = [n/1000,n%1000]
	D,remD = [remM/500,remM%500]
	C,remC = [remM/100,remM%100]
	L,remL = [remC/50,remC%50]
	X,remX = [remC/10,remC%10]
	V,remV = [remX/5,remX%5]
	I,remI = [remX/1,remX%1]
	val ='M'*M +'CM'*(remM/100==9) + \
		'D'*D*(remM<900) + 'CD'*(remM/100==4) + \
		'C'*(C%5)*(C%5<4) + 'XC'*(remC/10==9)+\
		'L'*L*(remC<90) + 'XL'*(remC/10==4) + \
		'X'*(X%5)*(X%5<4) + 'IX'*(remX==9) +\
		'V'*V*(remX<9) + 'IV'*(remX==4) + \
		'I'*(I%5) * (I%5<4)
	return val
