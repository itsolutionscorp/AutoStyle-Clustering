class Fixnum
  ROMAN_NUMERALS = {1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
    100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL', 10 => 'X', 9 => 'IX',
    5 => 'V', 4 => 'IV', 1 => 'I'}

  def to_roman
    roman_recursive(self, ROMAN_NUMERALS.keys).join
  end

  private

  def roman_recursive(n, stack)
    return [] if n == 0
    head = stack.first

    if n >= head
      [ROMAN_NUMERALS[head]].concat(roman_recursive(n - head, stack))
    else
      roman_recursive(n, stack[1..-1]) 
    end
  end
end
