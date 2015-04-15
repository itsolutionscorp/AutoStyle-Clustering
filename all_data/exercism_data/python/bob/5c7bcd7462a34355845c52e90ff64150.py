#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	bob = { "notCare"    : "Whatever.",
		    "freakedOut" : "Whoa, chill out!",
		    "goodGo"     : "Sure.",
		    "sarCasm"    : "Fine. Be that way!"
		  }
		
			
	if what.endswith('?') and not what.isupper():
		return bob['goodGo']
		
	if what.isupper():
		return bob['freakedOut']

	if isinstance(what,unicode) and not what.isupper() and not what.islower() and what and not what.isspace():
		return bob['notCare']
		
	if isinstance(what,unicode) and what.isupper():
		return bob['freakedOut']
		
	if what.find("?") != -1:
		return bob['notCare']		
		
	if not what.strip():
		return bob['sarCasm']
		
	if what.strip() and what.isspace() and what:
		return bob['freakedOut']
		
	if what.strip() and what.isspace() and not what:
		return bob['sarCasm']
		
	if what.strip() and what:
		return bob['notCare']
		
	if what.isdigit():
		return bob['notCare']
