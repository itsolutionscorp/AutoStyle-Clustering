import string

ordered="abcdefghijklmnopqrstuvwxyz"
backwards="zyxwvutsrqponmlkjihgfedcba"

def decode(s):
    return s.lower().translate(string.maketrans(backwards, ordered), ' ')

def encode(s):
    return string.join(chunkstring(s.lower().translate(string.maketrans(ordered, backwards), ' ,.!?'), 5), ' ')

# from http://stackoverflow.com/questions/18854620/whats-the-best-way-to-split-a-string-into-fixed-length-chunks-and-work-with-the
def chunkstring(string, length):
    return (string[0+i:length+i] for i in range(0, len(string), length))
