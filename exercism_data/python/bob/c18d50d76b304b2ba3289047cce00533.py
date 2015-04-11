import re
import nltk
import string

try:
    SENT_DETECTOR = nltk.data.load("tokenizers/punkt/english.pickle")
## nltk data must be installed for nltk.data.load to work
except LookupError:
    if nltk.download():
        SENT_DETECTOR = nltk.data.load("tokenizers/punkt/english.pickle")
    else:
        sys.exit("nltk download did not successfully complete")

def hey(s):
    ## if not a string, attempt to cast to a string
    if not isinstance(s, basestring):
        try:
            s = str(s)
        except:
            return "Whatever."
    
    if is_yell(s):
        return "Woah, chill out!"
    elif is_question(s):
        return "Sure."
    elif is_silence(s):
        return "Fine. Be that way!"
    else:
        return "Whatever."
    
def is_yell(s):
    ## check if string is change by lower but not by upper
    return s == string.upper(s) != string.lower(s)
    
def is_question(s):
    ## check for sentence ending with a non white 
    ## space character followed by a question mark
    return any([re.search("\S\?$", x) for x in SENT_DETECTOR.tokenize(s)])
    
def is_silence(s):
    ## check if any non-whitespace characters are present
    return not s.strip()        
