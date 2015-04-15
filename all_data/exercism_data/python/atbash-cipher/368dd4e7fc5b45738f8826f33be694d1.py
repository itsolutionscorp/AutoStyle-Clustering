alpha = list("abcdefghijklmnopqrstuvwxyz")
backalpha = list("zyxwvutsrqponmlkjihgfedcba")
cipher = list(zip(alpha, backalpha))

def encode(words):
    ciphered = ""
    for x in words.lower():
        if x.isalpha():
            for y, z in cipher:
               if x == y:
                    ciphered += z
        elif x.isnumeric():
            ciphered += x

    return " ".join(ciphered[i:i+5] for i in range(0, len(ciphered), 5))

def decode(words):
    deciphered = ""
    for x in words.lower():
        if x.isalpha():
            for y, z in cipher:
                if x == z:
                    deciphered += y
        elif x.isnumeric():
            deciphered += x
    return deciphered
