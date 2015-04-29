def hey(input):
	return "Fine. Be that way!" if input.strip() == "" else "Whoa, chill out!" if input.strip().isupper() else "Sure." if input.strip().endswith("?") else "Whatever."
