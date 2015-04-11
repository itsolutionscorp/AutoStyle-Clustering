def check_lists(a, b):
	if len(b) > len(a):
		if _is_superlist(b, a):
			return SUBLIST
		return UNEQUAL
	elif _is_superlist(a, b):
		if len(a) != len(b):
			return SUPERLIST
		return EQUAL
	else:
		return UNEQUAL


def _is_superlist(a, b):
	""" True if `b` is a sublist of `a`, otherwise False.
	"""

	# corner cases
	if len(b) > len(a):
		return False
	if len(b) <= 0:
		return True

	# Ok, jokes are over. Here's the loop.
	possible_start_indices = {}
	for a_i, a_el in enumerate(a):

		# Loop invariants at the start of each iteration:
		# 1. `possible_start_indices` contains a list of positions `start_i` < `a_i`
		#    such that `a[start_i:a_i] == b[0:a_i-start_i]`.
		# 2. we're not finished yet, i.e. all candidates are shorter than `b`:
		#    `len(b) > a_i-start_i` for each `start_i`

		for start_i in list(possible_start_indices.keys()):
			# Remove positions from `possible_start_indices`
			# that no longer satisfy the invariant.
			if a_el != b[a_i - start_i]:
				del possible_start_indices[start_i]
			# Found a matching sublist?
			elif a_i - start_i == len(b) - 1:
				return True

		# Add current position to `possible_start_indices`
		# if it satisfies the invariant
		if a_el == b[0]:
			possible_start_indices[a_i] = True
			# This ensures invariant 2, i.e. we're not finished yet
			if len(b) == 1:
				return True

	# Because of invariant 2, `b` is not a sublist of `a`
	return False


SUPERLIST = 'SUPERLIST'
SUBLIST   = 'SUBLIST'
UNEQUAL   = 'UNEQUAL'
EQUAL     = 'EQUAL'
