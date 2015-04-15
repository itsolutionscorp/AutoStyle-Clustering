#
# Models Behavior of Teenager, Bob, and provides canned responses to various strings
# 2nd iteration of submission, using phrase from other submitted exercises

def hey(phrase):

    phrase=phrase.strip()

    #Check if yelling
    if phrase.upper() == phrase and any(letter.isalpha() for letter in phrase):
        return "Whoa, chill out!"
   
    #Check if question
    if phrase.endswith('?'): return "Sure."

    #Check if empty, i.e.that 'phrase' doesn't contain any numbers or letters.  
    if not any((c.isalpha() or c.isdigit()) for c in phrase):
        return "Fine. Be that way!"

    # Normal response, catch all
    else: return "Whatever."
