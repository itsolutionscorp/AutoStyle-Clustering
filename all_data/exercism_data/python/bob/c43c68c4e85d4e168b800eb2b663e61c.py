#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """lackadaisical teenager simulator, should ideally be written in laid back"""
    if what.isupper():
        """Shouting in ALL CAPS. 
        So much easier once you learn to use the caps lock key..."""
        answer='Whoa, chill out!'
    elif what.strip().endswith('?'):
        """Questions end with a questionmark"""
        answer='Sure.'
    elif what.isspace() or what=="":
        """You can be passive aggressive in different ways"""
        answer='Fine. Be that way!'
    else:
        """Seriously ... whatever"""
        answer='Whatever.'
    return answer
