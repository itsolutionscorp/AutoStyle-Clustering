class Fixnum
  # Returns a string containing the roman numeral representation.
  #
  # @return [String] The roman numeral as a string.
  #   The empty string if self == 0.
  # @raise [RangeError] if called on a negative number.
  def to_roman
    fail RangeError "Romans didn't use negative numbers #{self}" if self < 0

    x = self
    roman_digits = [[1, 'I'], [4, 'IV'], [5, 'V'], [9, 'IX'],
                    [10, 'X'], [40, 'XL'], [50, 'L'], [90, 'XC'],
                    [100, 'C'], [400, 'CD'], [500, 'D'], [900, 'CM'],
                    [1000, 'M']].reverse

    roman_digits.inject('') do |acc, (decimal, roman)|
      factor, x = x.divmod(decimal)
      acc << roman * factor
    end
  end
end
