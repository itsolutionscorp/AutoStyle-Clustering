def numeral(arabic):

  symbol_table = [[0, 1000, '', 'M'], [900, 0, 'C', 'M'], [500, 100, 'D', 'C'], [400, 0, 'C', 'D'], [0, 100, '', 'C'], [90, 0, 'X', 'C'], [50, 10, 'L', 'X'], [40, 0, 'X', 'L'], [0, 10, '', 'X'], [9, 0, 'I', 'X'], [5, 1, 'V', 'I'], [4, 0, 'I', 'V'], [0, 1, '', 'I']]

  roman = []

  for symbol_list in symbol_table:
    if ((symbol_list[0] and arabic >= symbol_list[0]) or (not symbol_list[0] and arabic >= symbol_list[1])):
      arabic, roman = compute(arabic, roman, *symbol_list)

  return "".join(roman)


def compute(arabic, roman, value_first, value_second, symbol_first, symbol_second):
  
  if (value_second != 0): 
    num = int((arabic - value_first) / value_second)
  else:
    num = 1
  roman.append(symbol_first + symbol_second * num)
  return arabic - (value_first + value_second * num), roman
