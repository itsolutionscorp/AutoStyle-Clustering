# I use a dictionary to determine which letter to transcribe to, and then I
# append the letters to a new string. I'm not certain that what I'm doing is
# very efficient. I'd like something like the append method for lists, but
# string don't have an append. Any suggestions out there?

def to_rna(string):
    transcriptionDict = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}
    newString = ''
    for letter in string:
        newString += transcriptionDict[letter]
        
    return newString
