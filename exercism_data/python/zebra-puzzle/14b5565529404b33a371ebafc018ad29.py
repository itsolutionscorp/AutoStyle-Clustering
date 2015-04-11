from itertools import permutations

def solution():
	for c in permutations(['red', 'blue', 'green', 'ivory', 'yellow']):
		rc = {k: v for k, v in zip(c, range(5))}
		if not (rc['green'] - rc['ivory'] == 1):
			continue
		for n in permutations(['Englishman', 'Japanese', 'Norwegian', 'Spaniard', 'Ukrainian']):
			rn = {k: v for k, v in zip(n, range(5))}
			if not (n[rc['red']] == 'Englishman' and
				rn['Norwegian'] == 0 and
				abs(rn['Norwegian'] - rc['blue']) == 1):
				continue
			for d in permutations(['water', 'orange juice', 'tea', 'milk', 'coffee']):
				rd = {k: v for k, v in zip(d, range(5))}
				if not (d[rc['green']] == 'coffee' and
					d[rn['Ukrainian']] == 'tea' and
					rd['milk'] == 2):
					continue
				for s in permutations(['Kools', 'Parliaments', 'Old Gold', 'Lucky Strike', 'Chesterfields']):
					rs = {k: v for k, v in zip(s, range(5))}
					if not (s[rc['yellow']] == 'Kools' and
						d[rs['Lucky Strike']] == 'orange juice' and
						n[rs['Parliaments']] == 'Japanese'):
						continue
					for a in permutations(['fox', 'horse', 'snails', 'dog', 'zebra']):
						ra = {k: v for k, v in zip(a, range(5))}
						if (a[rn['Spaniard']] == 'dog' and
							a[rs['Old Gold']] == 'snails' and
							abs(rs['Chesterfields'] - ra['fox']) == 1 and
							abs(rs['Kools'] - ra['horse']) == 1):
							return ("It is the {} who drinks the water.\nThe {} keeps the zebra.".format(n[rd['water']], n[ra['zebra']]))
