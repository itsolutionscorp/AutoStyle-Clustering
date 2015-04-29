"""Bob. Give smarmy replies to pointless inputs."""
import re, unicodedata

def strip_accents(accented_string):
    """Strip accents off of characters and return ASCII equivalents.

    Lifted from:
    http://stackoverflow.com/questions/517923/what-is-the-best-way-to-remove-accents-in-a-python-unicode-string
    """
    return ''.join(c for c in unicodedata.normalize('NFD', accented_string)
                   if unicodedata.category(c) != 'Mn')

def hey(message):
    """Respond to inane statements and questions."""

    # Strip accents to make processing easier.
    message = strip_accents(message)

    if re.match(r'[A-Z]+$', re.sub('[^a-z]', '', message, flags=re.IGNORECASE)):
        # Any statement containing only capital letters.
        return 'Whoa, chill out!'
    elif re.search(r'\?$', message):
        # All questions.
        return "Sure."
    elif re.match(r'[\s\t]*$', message):
        # Statements containing only whitespace (spaces and/or tabs).
        return "Fine. Be that way!"
    else:
        # Any other statement.
        return "Whatever."
