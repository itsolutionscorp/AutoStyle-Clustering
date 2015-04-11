valid_digits = "0123456789abcdef"
hexa_table = { "0": 0, "1": 1, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "a": 10, "b": 11, "c": 12, "d": 13, "e": 14, "f": 15 }

def hexa(code):

  code = code.lower()
  if any([ digit not in valid_digits for digit in code ]):
    raise ValueError
  code = code[::-1]
  return sum ([ hexa_table[code[i]] * (16**i) for i in range(len(code)) ]) 
