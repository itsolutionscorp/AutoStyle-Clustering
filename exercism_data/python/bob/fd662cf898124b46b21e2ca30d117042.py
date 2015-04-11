def hey(input):

	has_alpha = False
	has_num = False
	for i in input:
		if i.isalpha():
			has_alpha = True
		elif i.isnumeric():
			has_num = True

	if not has_alpha and not has_num:
		return "Fine. Be that way!"

	if input.upper() == input and has_alpha:
		return "Whoa, chill out!"

	if input[-1] == "?":
		return "Sure."

	return "Whatever."
