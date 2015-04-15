module NumericConversions
  def to_roman
    fail ArgumentError, 'Number must be <= 3000' if self > 3000
    convert(decompose(self))
  end

  def decompose(number)
    number.to_s.chars
  end

  def convert(decomposed_decimal)
    decimal_place = 1
    result = []
    decomposed_decimal.reverse.each do |element|
      result << lookup_roman(element.to_i, decimal_place)
      decimal_place *= 10
    end
    result.reverse.join
  end

  def lookup_roman(units, decimal_place)
    return if units == 0
    dictionary = { 1000 => %w(M MM MMM),
                   100  => %w(C CC CCC CD D DC DCC DCCC CM),
                   10   => %w(X XX XXX XL L LX LXX LXXX XC),
                   1    => %w(I II III IV V VI VII VIII IX) }
    dictionary[decimal_place][units - 1]
  end
end

Fixnum.include NumericConversions
