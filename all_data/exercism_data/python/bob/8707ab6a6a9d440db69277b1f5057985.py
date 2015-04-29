#bob.py exercism testing module by Jeddf

def hey(input):
    # Silence check
    if len(input.strip()) == 0:
        return 'Fine. Be that way!'

    # SHOUTING check (only counts if it has letters)
    if input.upper() == input:
        if any(char.isalpha() for char in input): # <-Stolen from stack overflow
            return 'Whoa, chill out!'

    # Question check
    if input[-1] == '?':
        return 'Sure.'

    # Whatever.
    else:
        return 'Whatever.'
