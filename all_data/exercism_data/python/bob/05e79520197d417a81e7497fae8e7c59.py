#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    ans1="Sure.";
    ans2="Whoa, chill out!"
    ans3="Fine. Be that way!"
    ans4="Whatever." 
    if what.strip().endswith('?') and not what.isupper():
	return(ans1)
    if what.strip().isupper():
        return(ans2)
    if not what.strip():
	return(ans3)
    else:
    	return(ans4)
