from string import ascii_lowercase, whitespace, punctuation, digits

encode_dict = dict(zip(ascii_lowercase, ascii_lowercase[::-1]))
decode_dict = dict(zip(ascii_lowercase[::-1], ascii_lowercase))

def decode(message):
    return "".join([decode_dict[char] for char in message if char != " " ])

def encode(message):
    encoded_char_list = []
    for char in message.lower():
        #digits remain the same
        if char in digits:
            encoded_char_list.append(char)
        #ignore whitespace and punctuation
        elif char not in whitespace and char not in punctuation:
            encoded_char_list.append(encode_dict[char])

    # chunk characters into 5 letter strings
    chunks = ["".join(encoded_char_list[x:x+5]) for x in range(0, len(encoded_char_list), 5)]
    return " ".join(chunks)
