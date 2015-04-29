

plain = "abcdefghijklmnopqrstuvwxyz"
cipher = "zyxwvutsrqponmlkjihgfedcba"

def encode(convert_str):

    returnString = []
    str_splitter = 0

    for i, j in enumerate(convert_str.lower()):

        # If char is a number, it is added to the result
        if j.isdigit():
            returnString.append(j)
            str_splitter += 1
            if str_splitter % 5 == 0: returnString.append(" ")

        # ensure that input is a word character
        elif ord('a') <= ord(j) <= ord('z'):

            # add char at cipher position that corresponds to the position in the plain
            returnString.append(cipher[plain.index(j)])

            # insert space every 5th element of result string
            str_splitter += 1
            if str_splitter % 5 == 0: returnString.append(" ")


    return "".join(returnString).strip()


def decode(convert_str):

    returnString = []

    for i, j in enumerate(convert_str.lower()):
        if j.isalpha():
            returnString.append(plain[cipher.index(j)])

    return "".join(returnString)


