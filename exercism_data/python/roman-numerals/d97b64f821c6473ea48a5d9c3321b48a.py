def numeral(n):
	M,remM = [n/1000,n%1000]
	D,remD = [remM/500,remM%500]
	C,remC = [remM/100,remM%100]
	L,remL = [remC/50,remC%50]
	X,remX = [remC/10,remC%10]
	V,remV = [remX/5,remX%5]
	I,remI = [remX/1,remX%1]
	print [M,D,C,L,X,V,I]
	print [remM,remD,remC,remL,remX,remV,remI]
	print [C%5,C<4]
	val ='M'*M +'CM'*(remM==900) + \
		'D'*D*(remM<900) + 'CD'*(remM==400) + \
		'C'*(C%5)*(C%5<4) + 'XC'*(remC==90)+\
		'L'*L*(remC<90) + 'XL'*(remC==40) + \
		'X'*(X%5)*(X%5<4) + 'IX'*(remX==9) +\
		'V'*V*(remX<9) + 'IV'*(remX==4) + \
		'I'*(I%5) * (I%5<4)
	return val
