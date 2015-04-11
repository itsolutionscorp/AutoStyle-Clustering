alphabet='abcdefghijklmnopqrstuvwxyz';

def encode(cleantext):
        cyphertext = '';
        addspace = 5;
        for letter in cleantext:

                if letter == ' ' or letter == '.' or letter == ',':
                        continue;
                
                if addspace == 0:
                        cyphertext = cyphertext + ' ';
                        addspace = 5;        

                if letter.isdigit():
                        cyphertext = cyphertext + letter;
                        addspace = addspace - 1;
                        continue;

                indextoalpha = -alphabet.find(letter.lower()) - 1;
                cyphertext = cyphertext + alphabet[indextoalpha];
                addspace = addspace - 1;
                
        return cyphertext;

def decode(cyphertext):
        cleantext = '';
        for letter in cyphertext:
                if letter == ' ' or letter == '.':
                        continue;

                indextoalpha = -alphabet.find(letter.lower()) - 1;
                cleantext = cleantext + alphabet[indextoalpha];

        return cleantext;
