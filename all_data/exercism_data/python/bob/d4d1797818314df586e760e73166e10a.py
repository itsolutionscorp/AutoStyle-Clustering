# -*- coding: utf-8 -*-

# class bob:
#     def hey(s):
#         if s != "":
#             return "Whatever."


def hey(s):
	if s.isupper():
		return 'Whoa, chill out!'
	if len(s.strip()) == 0:
		return "Fine. Be that way!"
	if s[len(s)-1] == "?":
		return 'Sure.'
	else:
		return 'Whatever.'

if __name__ == '__main__':
	print hey("?")
	print hey('      ')
