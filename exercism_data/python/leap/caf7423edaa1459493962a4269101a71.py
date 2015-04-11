def is_leap_year(anno):
	if is_divisible_by_4(anno) and is_divisible_by_100(anno) and is_divisible_by_400(anno):
		return True
	elif is_divisible_by_4(anno) and not is_divisible_by_100(anno):
		return True
	else:
		return False


def is_divisible_by_4(anno):
	return anno % 4 == 0

def is_divisible_by_100(anno):
	return anno % 100 == 0

def is_divisible_by_400(anno):
	return anno % 400 == 0
