def distance(here,there):
    return sum(char1 != char2 for char1,char2 in zip(here.upper(),there.upper()))
