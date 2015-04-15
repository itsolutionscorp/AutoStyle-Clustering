alphabet = ('abcdefghijklmnopqrstuvwxyz')
rev_alph = (alphabet[::-1])
numbers = '123456789'
def decode(what):
    
    return i_help(what,rev_alph,alphabet)


def encode(what):
    
    result = i_help(what,alphabet,rev_alph)
    #this splits the string in 5 char pieces and then joins them with a space inbetween
    result = " ".join(result[i:i+5] for i in range(0, len(result), 5))
    return result


def i_help(what,alphabet,counter_alphabet):
    result = ''
    #if the character is in the given string it searches it's position and
    #replaces it with the character in that same position of the opposite string
    for i in what.lower():
        for count,j in enumerate(alphabet):
            
            if i==j:
                result += counter_alphabet[count]
        #if it's a number it stays as is
        if i in numbers:
            result += i
    #everything else gets ignored
    return result
