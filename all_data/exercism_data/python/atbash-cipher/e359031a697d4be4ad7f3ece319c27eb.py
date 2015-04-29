import string

def decode( word ):
    word = word.lower()
    LC = string.ascii_lowercase
    new_word = ''
    for let in word:
        if let in LC:
            new_word += LC[ 25 - LC.index( let ) ]
        elif let in string.digits:
            new_word += let
    return new_word

def add_spaces( word ):
    new_word = ''
    counter = 0
    for let in word:
        if counter % 5 == 0:
            new_word += ' '
        new_word += let
        counter += 1
    return new_word.strip()

def encode( word ):
    return add_spaces( decode( word ) )
