#bob.py exercism testing module by Jeddf

def hey(input):

    # Whitespace?! Who needs whitespace.
    
    input = input.strip()

    # Silence check
    if input == '':
        return 'Fine. Be that way!'

    # SHOUTING check (only counts if it has letters)
    if input.isupper():
        return 'Whoa, chill out!'

    # Question check
    if input[-1] == '?':
        return 'Sure.'

    # Whatever.
    else:
        return 'Whatever.'
