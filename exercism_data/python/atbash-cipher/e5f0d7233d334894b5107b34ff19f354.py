key_plain = list("abcdefghijklmnopqrstuvwxyz")
key_cipher = list("zyxwvutsrqponmlkjihgfedcba")
forbidden = list(" .,")
new_content = []
final_string = ""

def either_way(content):
    new_content = []
    final_string = ""
    content = content.lower()
    for letter in content:
        if letter in key_plain:
            new_content.append(key_cipher[key_plain.index(letter)])
        elif letter in forbidden:
            next
        else:
            new_content.append(letter)
    return new_content
            
def encode(content):
    new_content = either_way(content)
    n = 0
    for i in range(1,len(new_content)):
        if i%5 == 0:
            new_content.insert(i+n," ")
            n += 1
    return final_string.join(new_content)
    
def decode(content):
    new_content = either_way(content)
    return final_string.join(new_content)
