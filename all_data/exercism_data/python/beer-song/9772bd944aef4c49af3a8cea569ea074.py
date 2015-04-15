verse_template = {
    'line1': "{} of beer on the wall, {} of beer.\n",
    'line2': "Take {} down and pass it around, {} of beer on the wall.\n",
    'endline': "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
}

def verse(n):
    a,b = formatnum(n)
    line1 = verse_template['line1'].format(a.capitalize(), a)
    line2 = verse_template['line2'].format(b, formatnum(n-1)[0]) if n else verse_template['endline']
    
    return line1 + line2

def song(start, stop=0):
    return '\n'.join(verse(i) for i in xrange(start,stop-1,-1)) + '\n'

def formatnum(n):
    if n > 1:
        return (str(n) + ' bottles', 'one')
    elif n == 1:
        return ('1 bottle', 'it')
    else:
        return ('no more bottles', '')
