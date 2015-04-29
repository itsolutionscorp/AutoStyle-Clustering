import itertools


def solution():
	res = []
	houses = list(itertools.permutations([1,2,3,4,5]))
	for (red,green,ivory,yellow,blue) in houses:
		if green-1==ivory:
			for (Englishman,Spaniard,Ukrainian,Norwegian,Japanese) in houses:
				d = {Englishman:'Englishman',Spaniard:'Spaniard',Ukrainian:'Ukrainian',Norwegian:'Norwegian',Japanese:'Japanese'}
				if Englishman == red and Norwegian == 1:
					if Norwegian +1 ==blue or Norwegian -1 == blue:
						for (coffee,milk,tea,oj,water) in houses:
							if coffee==green and tea==Ukrainian and milk == 3:
								for (OldGold,Kools,Chesterfields,LuckyStrike,Parliaments) in houses:
									if Kools == yellow and Parliaments == Japanese and LuckyStrike == oj:
										for (dog,snails,fox,horse,zebra) in houses:
											if snails == OldGold and Spaniard==dog:
												if fox+1==Chesterfields or fox-1==Chesterfields:
													if horse+1==Kools or horse-1==Kools:
														res.append(d[water])
														res.append(d[zebra])
	return "It is the %s who drinks the water.\nThe %s keeps the zebra." % (res[0],res[1])





print solution()
