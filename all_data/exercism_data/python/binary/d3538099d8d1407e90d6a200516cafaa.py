def parse_binary(code):

  if any([ digit != '0' and digit != '1' for digit in code ]):
    raise ValueError
  code = code[::-1]
  return sum ([ 2**i for i in range(len(code)) if code[i] == '1' ]) 
