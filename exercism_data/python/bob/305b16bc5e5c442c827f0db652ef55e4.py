def hey(prompt):
    if (prompt.isupper()):
            return "Whoa, chill out!"
    if (prompt.endswith('?') ):
            return 'Sure.'
    if (prompt.strip()=='' ):
        return 'Fine. Be that way!'
    return 'Whatever.'
