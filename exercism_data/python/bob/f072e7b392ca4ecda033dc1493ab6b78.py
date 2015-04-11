import re


def hey(s):
    """
    Return 'Sure.' if a question is asked
    Return 'Whoa, chill out!' if you yell at him (caps)
    Return 'Fine. Be that way!' if you don't say anything
    Return 'Whatever.' to anything else.
    """

    # Define my regex definitions
    question = r"\?$"               # Check for question mark
    caps = r"[A-ZÜÄË]"              # Check for capital letters
    no_caps = r"[a-zäüé]"           # Check for lower case letters
                                    # Umlauts included where needed
    # Strip end of line terminators
    new_string = s.strip()

    # Work through the logic of the function
    if new_string == '':
        return 'Fine. Be that way!'
    elif re.search(caps, new_string) and not re.search(no_caps, new_string):
        return 'Whoa, chill out!'
    elif re.search(question, new_string):
        return 'Sure.'
    else:
        return 'Whatever.'
