import re
def hey(statement):
	question=re.compile('^\s*$');
	if question.match(statement):
		return "Fine. Be that way!"
	if statement.upper() == statement and statement.lower() != statement:
		return "Whoa, chill out!"
	if statement.endswith('?'):
		return "Sure."
	return "Whatever."

version = "1.0"
