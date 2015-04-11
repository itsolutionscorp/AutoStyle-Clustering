def board(t1,t2):
	_checks(t1,t2)
	layout = [['_']*8 for i in range(8)]
	layout[t1[0]][t1[1]] = 'W'
	layout[t2[0]][t2[1]] = 'B'
	return [''.join(layout[i]) for i in range(len(layout))]

def can_attack(t1,t2):
	_checks(t1,t2)
	condition = t1[0]==t2[0] or t1[1]==t2[1] or abs(t1[0]-t2[0])==abs(t1[1]-t2[1])
	return True if condition else False


def _checks(t1,t2):
	roam = range(8)
	itemList = [t1[0],t1[1],t2[0],t2[1]]
	for i in itemList:
		if t1 == t2 or i not in roam:
			raise ValueError("Queens must not overlap, and must be on board")
