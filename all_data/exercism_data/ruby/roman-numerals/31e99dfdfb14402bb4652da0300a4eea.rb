class Integer

  LITERALS = {
    1 => 'I',
    4 => 'IV',
    5 => 'V',
    9 => 'IX', 
    10 => 'X', 
    40 => 'XL',
    50 => 'L',
    90 => 'XC',
    100 => 'C',
    400 => 'CD',
    500 => 'D',
    900 => 'CM',
    1000 => 'M' 
  }
  NUMBERS = LITERALS.keys

  def to_roman
    romanized_number = "" # Empty string to store the Roman number
    number = self # Copying self (which is the number) to a variable
    iterator = NUMBERS.size - 1 # This gets the size of the NUMBERS constant and removes one so it gets the iterator right on the array

    while number > 0 # While the number is not zero (there are no zeros in Roman numbers)
      if number >= NUMBERS[iterator] # If the number is bigger than the NUMBERS position the iterator indicates
        romanized_number << LITERALS[NUMBERS[iterator]] # We add the corresponding letter to the string
        number -= NUMBERS[iterator] # And we remove that unit from the number
      else # Otherwise we remove 1 from the iterator
        iterator -= 1 
      end
    end

    romanized_number
  end

end
