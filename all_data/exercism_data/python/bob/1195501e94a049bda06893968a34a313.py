def hey(input):
    """Talk to Bob."""
    
    # Null check
    if not input.strip():
        return 'Fine. Be that way!'

    # Yelling
    # There are alphabetic characters, and all characters are upper case
    if any(c.isalpha() for c in input) and \
       all(c.isupper() for c in input if c.isalpha()):
        return 'Woah, chill out!'

    # Questions? 
    if input.endswith('?'):
        return 'Sure.'

    # Default
    return 'Whatever.'
