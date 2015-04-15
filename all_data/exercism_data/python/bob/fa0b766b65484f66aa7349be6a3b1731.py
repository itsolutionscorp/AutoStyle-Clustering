import re

def hey(text):
    """Respond to the given text as a typical lackadaisical teenager."""
    def is_shouting(text):
        return (text.upper() == text and 
            re.search(r"[A-Z]", text) != None)

    def is_empty(text):
        return len(text.strip()) == 0

    def is_question(text):
        return text[-1] == '?'

    if is_shouting(text):
        return 'Woah, chill out!'
    if is_empty(text):
        return 'Fine. Be that way!'
    if is_question(text):
        return 'Sure.'
    return 'Whatever.'
