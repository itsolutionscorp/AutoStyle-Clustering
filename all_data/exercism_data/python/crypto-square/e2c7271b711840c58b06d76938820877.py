from math import ceil

def encode(cleartext):
    """Encodes given text using the crypto square method"""

    #First process the text
    cleartext = [char.lower() for char in cleartext if char.isalnum()]

    #Then clear trivial cases
    if cleartext == []:
        return ''

    #Then turn the text into the broken out rows
    column_length = int(ceil(len(cleartext) ** 0.5))   
    clear_rows=[cleartext[column_length*i:column_length*i+column_length]
                for i in range(len(cleartext)//column_length+1)]
    if (clear_rows[len(clear_rows)-1]==[]):
        clear_rows = clear_rows[:-1]
    short_columns = column_length*len(clear_rows)-len(cleartext)

    #Then collect the text from the long columns...
    ciphertext = []
    for column in range(column_length-short_columns):
        for row in range(len(clear_rows)):
            ciphertext += clear_rows[row][column]
        ciphertext += [' ']

    #And then the shorter columns
    for column in range(column_length-short_columns, column_length):
        for row in range(len(clear_rows)-1):
            ciphertext += clear_rows[row][column]
        ciphertext += [' ']

    return ''.join(ciphertext[:-1])

def decode(ciphertext):
    """Decodes given text using the crypto square method"""
    #First reverse the words and put them into lists
    reverse_words = [word[::-1] for word in ciphertext.split()]
    reverse_words = [list(word) for word in reverse_words]
    
    #Next, pop letters off each word of the list until they're empty
    cleartext = []
    for i in range(len(reverse_words[0])):
        try:
            for j in range(len(reverse_words)):
                cleartext += list(reverse_words[j].pop())
        except IndexError: #This deals with the end of the shorter lists
            pass

    return ''.join(cleartext)
