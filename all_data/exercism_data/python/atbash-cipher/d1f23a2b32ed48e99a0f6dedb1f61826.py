import string


cipher = dict(zip(list("abcdefghijklmnopqrstuvwxyz"), list("zyxwvutsrqponmlkjihgfedcba")))
def encode(sentence):
    encode_result = []
    word = []
    for char in sentence.lower():
        if char == ' ' or char in string.punctuation:
            continue
        if len(word) == 5:
            encode_result.append("".join(word))
            word = []
        if char in cipher:
            word.append(cipher[char])
        else:
            word.append(char)
    encode_result.append("".join(word))
    return " ".join(encode_result)

def decode(sentence):
    decode_result = []
    for char in sentence.lower():
        if char == " ":
            continue
        if char in cipher:
            decode_result.append(cipher[char])
        else:
            decode_result.append(char)
    return "".join(decode_result)
