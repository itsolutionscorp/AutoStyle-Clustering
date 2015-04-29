from math import ceil

def encode(phrase):
    clean = "".join([c.lower() for c in phrase.strip() if c.isalpha() or c.isdigit()])
    square_length = ceil(len(clean)**0.5)
    square_list = []
    for i in range(square_length):
        square_list.append("")
    for j in range(len(clean)):
        square_list[j%square_length] += clean[j]
    return " ".join(square_list)
