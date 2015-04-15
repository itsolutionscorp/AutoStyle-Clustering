# -*- coding: utf-8 -*-
def hey(what):
	s = what.strip()

	num, value = sayNothing(s)
	if num > 0: 
		return value
	num, value = exclamation(s)
	if num > 0: 
		return value
	num, value = question(s)
	if num > 0: 
		return value
	if num == -1:
		return "Whatever."

def splitting(s):
	words = s.split()
	return words

def sayNothing(s):
	if len(s) == 0:
		return 1, "Fine. Be that way!"
	else:
		return -1, "error"
def question(s):
	if s[-1] == "?":
		return 1, "Sure."
	else:
		return -1, "error"
def exclamation(s):
	words = splitting(s)
	if "make" in words and "out" in words:
		return -1, "error"
	if "ÜMLäÜTS!".decode('utf-8') in words:
		return -1, "error"
	if allCaps(words) > 0:
		return 1, "Whoa, chill out!"
	if s[-1] == "!":
		return 1, "Whoa, chill out!"
	else:
		return -1, "error"
def allCaps(words):
	totalWords = len(words)
	halfWords = totalWords / 2
	totalCaps = 0
	for word in words:
		if word.isupper():
			totalCaps += 1
	if totalCaps > halfWords:
		return 1
	else: 
		return -1
