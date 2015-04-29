class Fixnum

  ROMANS = { 1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
              100 => 'C',  90 => 'XC',  50 => 'L',  40 => 'XL',
               10 => 'X',   9 => 'IX',   5 => 'V',   4 => 'IV',
                1 => 'I' }

  def to_roman
    roman_components.join
  end

  def roman_components found = ['']
    return found if self.eql? 0
    letter_value, letter = ROMANS.find { |k,v| self >= k }
    (self - letter_value).roman_components found << letter
  end
end
