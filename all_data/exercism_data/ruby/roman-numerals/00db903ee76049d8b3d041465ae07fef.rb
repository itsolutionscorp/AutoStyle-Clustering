class Fixnum

  ROMAN_NUMERAL_MAPPINGS = [
    {factor: 100, ones_char: 'C', fives_char: 'D', tens_char: 'M'},
    {factor:  10, ones_char: 'X', fives_char: 'L', tens_char: 'C'},
    {factor:   1, ones_char: 'I', fives_char: 'V', tens_char: 'X'},
  ]

  def to_roman
    if self > 3000
      raise "the Romans didn't tend to go any higher than 3000"
    end

    result = 'M' * (self / 1000)
    ROMAN_NUMERAL_MAPPINGS.each do |mapping|
      mod = (self / mapping[:factor]) % 10

      if  mod == 9
        result += mapping[:ones_char] + mapping[:tens_char]
      elsif mod >= 5
        result += mapping[:fives_char] + mapping[:ones_char] * (mod % 5)
      elsif mod == 4
        result += mapping[:ones_char] + mapping[:fives_char]
      else
        result += mapping[:ones_char] * mod
      end
    end

    return result
  end
  
end
