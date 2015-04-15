#Bob
def hey(ask_bob):
	if ask_bob.strip():
		if ask_bob.endswith("?"):
			bob_answer = "Sure."
		else:
			bob_answer = "Whatever."
		if ask_bob.isupper():
			bob_answer = "Whoa, chill out!"
	else:
		bob_answer = "Fine. Be that way!"

	return bob_answer
