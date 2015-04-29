def decode(message):
    decode_list = [atbash_char(l) for l in message.lower()]
    decode_list = alnum_list(decode_list)
    return ''.join(decode_list)

def encode(message):
    enc_list = [atbash_char(l) for l in message.lower()]
    enc_list = alnum_list(enc_list)
    for i in range(5, int(len(enc_list)*6/5) - 1, 6):
        enc_list.insert(i, ' ')
    return ''.join(enc_list)

def alnum_list(l): # return l with only alphanumeric elements remaining
    while '' in l:
        l.remove('')
    while not ''.join(l).isalnum():
        for i in l:
            if not i.isalnum():
                while i in l:
                    l.remove(i)
                break
    return l

def atbash_char(ch):
    if ch.isdigit():
        return ch
    if ch.isalpha():
        return chr(219 - ord(ch)) # ord('z') + ord('a') = 219
    else:
        return ''
