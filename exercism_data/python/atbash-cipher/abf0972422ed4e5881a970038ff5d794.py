ordered="abcdefghijklmnopqrstuvwxyz"
backwards="zyxwvutsrqponmlkjihgfedcba"

def decode(s):
    return s.lower().translate(str.maketrans(backwards, ordered, ' '))

def encode(s):
    return ' '.join(chunkstring(s.lower().translate(str.maketrans(ordered, backwards, ' ,.!?')), 5))

# from http://stackoverflow.com/questions/18854620/whats-the-best-way-to-split-a-string-into-fixed-length-chunks-and-work-with-the
def chunkstring(s, length):
    return (s[0+i:length+i] for i in range(0, len(s), length))
