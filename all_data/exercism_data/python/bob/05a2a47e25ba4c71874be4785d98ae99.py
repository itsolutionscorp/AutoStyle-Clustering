#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    answer='Whatever.'
    what=what.strip()
    if(what==''):
        return "Fine. Be that way!"
    words = [''.join(i for i in word  if i.isalpha()) for word in what.split(' ')]
    have_letters=any(c.isalpha() for c in what)
    all_words_isupper=(all((word.isupper() or word=='') for word in words))
    if have_letters and all_words_isupper :
        return 'Whoa, chill out!'
    if(len(what)>1) and (what[-1]=='?'):
        return "Sure."
    return answer
