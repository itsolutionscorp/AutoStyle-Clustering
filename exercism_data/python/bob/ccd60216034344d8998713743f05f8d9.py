def hey(text):
    """Respond to the given text as a typical lackadaisical teenager."""
    def is_shouting(text):
        return text.isupper()

    def is_silent(text):
        return len(text.strip()) == 0

    def is_question(text):
        return text.endswith('?')

    if is_silent(text):
        return 'Fine. Be that way!'
    if is_shouting(text):
        return 'Woah, chill out!'
    if is_question(text):
        return 'Sure.'
    return 'Whatever.'
