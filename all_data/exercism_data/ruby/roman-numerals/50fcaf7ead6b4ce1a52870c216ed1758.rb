class RomanCalculator

  MAPPINGS = {
    1000 => 'M',
    900  => 'CM',
    500  => 'D',
    400  => 'CD',
    100  => 'C',
    90   => 'XC',
    50   => 'L',
    40   => 'XL',
    10   => 'X',
    9    => 'IX',
    5    => 'V',
    4    => 'IV',
    1    => 'I'
  }

  attr_reader :number, :roman_numeral

  def initialize(number)
    @number        = number
    @roman_numeral = ''
  end

  def to_roman
    while @number > 0 do
      MAPPINGS.each do |k, v|
        if @number >= k
          @roman_numeral << v
          @number -= k
          break
        end
      end
    end
    @roman_numeral
  end

end

class Fixnum

  def to_roman
    RomanCalculator.new(self).to_roman
  end

end
