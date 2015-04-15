def is_divisor_of(first_number, second_number): 
  remainder = first_number % second_number
  return remainder == 0 

def is_divisor_of_four(number): 
  return is_divisor_of(number, 4)

def is_divisor_of_a_hundred(number): 
  return is_divisor_of(number, 100)

def is_divisor_of_four_hundred(number):  
  return is_divisor_of(number, 400)

def is_leap_year(year):
  return (is_divisor_of_four(year) and 
    ((not is_divisor_of_a_hundred(year)) or
      is_divisor_of_four_hundred(year)))
