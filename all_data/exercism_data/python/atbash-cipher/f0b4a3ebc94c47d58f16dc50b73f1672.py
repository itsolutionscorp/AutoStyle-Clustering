def decode(message):
    decode_list = [atbash_char(l) for l in message.lower() if l.isalnum()]
    return ''.join(decode_list)

def encode(message):
    enc_list = [atbash_char(l) for l in message.lower() if l.isalnum()]
    return ' '.join([''.join(enc_list[i:i+5]) for i in range(0, len(enc_list), 5)])

def atbash_char(ch):
    if ch.isdigit():
        return ch
    if ch.isalpha():
        return chr(219 - ord(ch)) # ord('z') + ord('a') = 219
    raise ValueError('atbash_char can only handle alphanumeric characters.')
