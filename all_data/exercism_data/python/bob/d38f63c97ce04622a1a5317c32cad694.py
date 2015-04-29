import re
import unicodedata

def replace_nonascii_characters(input_str):
    nkfd_form = unicodedata.normalize('NFKD', input_str)
    only_ascii = nkfd_form.encode('ASCII', 'ignore')
    return only_ascii.decode('ascii')

def hey(what):
    # use an ASCII representation of all umlauts encountered
    what = replace_nonascii_characters(what)

    if not re.search(r'[a-zA-Z0-9]', what):
        return "Fine. Be that way!"

    # yelling?
    if re.search(r'[A-Z]', what) and not re.search(r'[a-z]', what):
        # there's at least one uppercase letter and no lowercase letters
        return "Whoa, chill out!"

    # a question?
    if what.endswith('?'):
        return "Sure."

    # default: attitude
    return "Whatever."
