class Fixnum

  ROMANS = { 1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
              100 => 'C',  90 => 'XC',  50 => 'L',  40 => 'XL',
               10 => 'X',   9 => 'IX',   5 => 'V',   4 => 'IV',
                1 => 'I' }

  def to_roman
    roman_components(self).join
  end

  private

    def roman_components remaining, found = ['']
      return found if remaining.eql? 0
      letter_value, letter = ROMANS.find { |k,v| remaining >= k }
      roman_components (remaining - letter_value), found << letter
    end
end
