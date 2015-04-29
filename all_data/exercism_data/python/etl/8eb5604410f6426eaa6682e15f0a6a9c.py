def check_alpha(allwords):
	if len(allwords) != 26:
		return False
	s = set()
	for i in allwords:
		if i.lower() in s or not i.isalpha(): 
			return False
		s.add(i.lower())
	return True 

def transform(old_db):
	new_db = {}
	old_keys = old_db.viewkeys()
	if check_alpha("".join(["".join([i for i in old_db[j]]) for j in old_keys])):
		for i in old_keys:
			for char in old_db[i]:
				new_db[char.lower()] = i
	else:
		for i in old_keys:
			for word in old_db[i]:
				new_db[word.lower()] = i
	return new_db
