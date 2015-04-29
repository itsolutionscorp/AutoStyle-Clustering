def hey(prompt):
    if (prompt.endswith('?') and (prompt!=prompt.upper() or not any(c.isalpha() for c in prompt))):
            return 'Sure.'
    if (prompt.strip()=='' or not any(c.isalpha() for c in prompt) ):
        return 'Fine. Be that way!'
    if (prompt==prompt.upper()):
        return "Whoa, chill out!"
    return 'Whatever.'
