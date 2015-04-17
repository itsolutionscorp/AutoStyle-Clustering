import string
def word_count(wds):
    """Returns a dictionary of {str('word'):int(count),...}
    """
    np = wds.lower().translate(None, string.punctuation).split()
    return { y : len([z for z in np if z == y]) for y in set(np) }
if __name__ == '__main__':
    print 'This script is not meant to be used this way.'
