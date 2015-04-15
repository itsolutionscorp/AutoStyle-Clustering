class Integer
  ROMAN_NUMERALS = %w(IVX XLC CDM M)
  ROMAN_PATTERNS = ['', '1', '11', '111', '15', '5', '51', '511', '5111', '10']

  def to_roman
    fail ArgumentError, "No roman numeral greater than M" if self >= 4000

    digits_reverse.each_with_index.map do |digit, power|
      ROMAN_PATTERNS[digit].tr('150', ROMAN_NUMERALS[power])
    end.reverse.join
  end

  def digits(radix = 10)
    digits_reverse(radix).reverse_each
  end

  def digits_reverse(radix = 10)
    DigitsReverse.new(self, radix)
  end
end

class DigitsReverse
  include Enumerable

  def initialize(integer, radix = 10)
    @integer = integer
    @radix = radix
  end

  def each
    remainder = @integer

    while remainder >= @radix
      (remainder, modulus) = remainder.divmod @radix
      yield modulus
    end

    yield remainder
  end
end
