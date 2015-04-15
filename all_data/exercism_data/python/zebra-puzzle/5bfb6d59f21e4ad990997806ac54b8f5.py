import itertools

def isright(h1,h2):
	return h1-h2 == 1

def isneighbor(h1,h2):
	return abs(h1-h2) == 1

def namestr(obj,namespace):
	return [name for name in namespace if namespace[name] is obj]

def solution():
	init = range(1,6)
	first = init[0]
	middle = init[2]
	perms = list(itertools.permutations(init))
	for (red, green, yellow, ivory, blue) in perms:
		if isright(green,ivory):
			for (english,spanish,ukrainian,norwegian,japanese) in perms:
				if english == red and norwegian == 1 and isneighbor(norwegian,blue):
					for (dog,snail,fox,zebra,horse) in perms:
						if spanish == dog:
							for (oldgold,kools,luckystrikes,parliaments,chesterfields) in perms:
								if kools == yellow and oldgold == snail and isneighbor(fox,chesterfields) and isneighbor(kools,horse) and japanese == parliaments:
									for (coffee,tea,milk,oj,water) in perms:
										if ukrainian == tea and coffee == green and milk == middle and luckystrikes == oj:
											waterguy = [name for name in namestr(water,locals()) if name in ['english','spanish','ukrainian','norwegian','japanese']][0].title()
											zebraguy = [name for name in namestr(zebra,locals()) if name in ['english','spanish','ukrainian','norwegian','japanese']][0].title()
											return "It is the {0} who drinks the water.\nThe {1} keeps the zebra.".format(waterguy,zebraguy)
