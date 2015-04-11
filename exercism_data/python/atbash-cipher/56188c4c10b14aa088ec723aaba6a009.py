alphabet = 'abcdefghijklmnopqrstuvwxyz1234567890'
alphabetRev = 'zyxwvutsrqponmlkjihgfedcba1234567890'
mapping = dict(zip(alphabet,alphabetRev))

def encode(text):
    mappedText = mapText(text)
    return ' '.join([''.join(mappedText[i:i+5]) for i in range(0,len(mappedText),5)])

def decode(text):
    return ''.join(mapText(text))

def mapText(text):
    filteredText = filter(str.isalnum,text.replace(' ','').lower())
    return map(lambda c: mapping[c],filteredText)
