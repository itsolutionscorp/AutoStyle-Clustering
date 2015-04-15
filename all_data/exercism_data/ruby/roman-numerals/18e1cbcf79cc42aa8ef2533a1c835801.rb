class RomanNumeral
  def initialize(number)
    fail ArgumentError unless number.is_a?(Integer)
    @number = number
    @numeral = ''
  end

  def convert_from_number
    return 'There is no Roman Numeral for 0' if @number == 0
    numeral_conversion
    @numeral
  end

  private

  def numeral_conversion(number = @number)
    numeral_conversion_values.keys.each do |divisor|
      result, modulus = number.divmod(divisor)
      @numeral << numeral_conversion_values[divisor] * result
      return numeral_conversion(modulus) if result > 0
    end
  end

  def numeral_conversion_values
    {
      1000 => 'M',
      900 => 'CM',
      500 => 'D',
      400 => 'CD',
      100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
      9 => 'IX',
      5 => 'V',
      4 => 'IV',
      1 => 'I'
    }
  end
end

class Integer
  def to_roman
    RomanNumeral.new(self).convert_from_number
  end
end
