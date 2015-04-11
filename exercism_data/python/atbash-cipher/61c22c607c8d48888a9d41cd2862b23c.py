from string import ascii_lowercase


def encode(string):
    plain = ascii_lowercase
    cipher = ascii_lowercase[::-1]
    numbers = ''.join(map(str, range(10)))

    encoder = dict(zip((plain+numbers), (cipher+numbers)))

    encoded = ''.join(encoder[x] for x in string.lower() if x in plain+numbers)
    return ''.join([encoded[i:i+5] + ' 'for i in range(0, len(encoded), 5)]).strip()


def decode(string):
    plain = ascii_lowercase
    cipher = ascii_lowercase[::-1]
    numbers = ''.join(map(str, range(10)))

    decoder = dict(zip((plain+numbers), (cipher+numbers)))

    return ''.join(decoder[x] for x in string.lower() if x in plain+numbers)

if __name__ == "__main__":
    print('ab yz!')
    print(encode('abyz'))
