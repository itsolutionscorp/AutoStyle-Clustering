def hey(phrase):
    sure = 'Sure.'
    woah = 'Whoa, chill out!'
    fine = 'Fine. Be that way!'
    whatever = 'Whatever.'


    if phrase.isupper():
        return woah
    elif phrase.endswith("?"):
        return sure 
    elif phrase.isspace() or not phrase:
        return fine
    else:
        return whatever
