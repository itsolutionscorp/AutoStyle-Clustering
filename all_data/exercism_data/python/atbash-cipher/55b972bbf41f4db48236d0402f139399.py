alphabet = "abcdefghijklmnopqrstuvwxyz"
normal = [c for c in alphabet[::]]
encoded = [c for c in alphabet[::-1]]

def encode(message):
    result = ""
    for item in message.split():
        for c in item:
            if c.isalpha():
                result += encoded[normal.index(c.lower())]
            elif c.isdigit():
                result += c
    return " ".join(result[i:i+5] for i in range(0, len(result), 5))

def decode(message):
    result = ""
    for item in message.split():
        for c in item:
            if c.isalpha():
                    result += normal[encoded.index(c.lower())]
    return result








