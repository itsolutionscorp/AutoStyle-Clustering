import string

# Store the alphabets for easy reference
PLAIN_ALPHABET = string.ascii_lowercase
CIPHER_ALPHABET = PLAIN_ALPHABET[::-1]

# Translating tables for easy encoding/decoding
ENCODING_TABLE = string.maketrans(PLAIN_ALPHABET,CIPHER_ALPHABET)
DECODING_TABLE = string.maketrans(CIPHER_ALPHABET,PLAIN_ALPHABET)

# Decode a ciphered message
def decode(message):

    # Validate input
    if not isinstance(message,basestring):
        return None

    # Since the message is already encoded we assume there is no need to convert anything to lower case

    # Decode message using the decoding table. Also remove punctuation and whitespace characters
    return message.translate(DECODING_TABLE,string.punctuation+string.whitespace)

# Encode a plain message
def encode(message):

    # Validate input
    if not isinstance(message,basestring):
        return None

    # First convert all upper case characters into lower case
    message = message.lower()

    # Encode message using the encoding table. Also remove punctuation and whitespace characters
    message = message.translate(ENCODING_TABLE,string.punctuation+string.whitespace)

    # Insert a space every 5 characters and remove any extra spaces leftover at the end
    return ''.join([message[i] + ' ' if (i+1) % 5 == 0 else message[i] for i in range(len(message)) ]).rstrip()
