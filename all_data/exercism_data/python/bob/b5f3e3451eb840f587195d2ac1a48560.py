import re

_delim_punct = re.compile(r'([\.?!]+)(?: |$)')
_questionmarks = re.compile(r'^\?+$')

def hey(phrase):   
    phrase = phrase.strip()
    
    # My interpretation of the vague specifications:
    # 
    # Bob reacts to the last sentence.
    #
    # If the sentence is in all caps, he replies "Whoa, chill out!"
    # If the sentence ends with more than one question mark
    #  and no exclamation marks (no interrobangs allowed),
    #  it is a question and Bob replies "Sure."
    # If the sentence is all whitespace, he says "Fine. Be that way!"
    # In any other case, the reply is "Whatever."
    #
    # Also, I assume there is never a space before the end-of-sentence
    # punctuation.
    #
    
    # First, check for empty sentences
    
    if not phrase:
        return 'Fine. Be that way!'
    
    # Second, get the last sentence
    
    parts = _delim_punct.split(phrase)
    if not parts[-1]: # the RE matches at the end of the string
        parts.pop()
    else: # There was no punctuation at the end
        parts.append('')
       
    # If the last sentence is all caps:
    if parts[-2].isupper():
        return 'Whoa, chill out!'
    
    # If the punctuation is questionmarks
    if _questionmarks.match(parts[-1]):
        return 'Sure.'
    
    return 'Whatever.'
