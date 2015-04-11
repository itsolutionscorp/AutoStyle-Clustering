#NOT my oringial work
#Borrowed with gratitude from https://github.com/alxndr/exercism/blob/master/ruby/roman-numerals/roman.rb

module RomanNumerable

  ROMAN_NUMERABLE_MAPPING = {
       1 => 'I',
       5 => 'V',
      10 => 'X',
      50 => 'L',
     100 => 'C',
     500 => 'D',
    1000 => 'M'
  }

  def to_roman
    num_in_4_digits = '%04d' % self
    num_in_4_digits.chars.each_with_index.map do |n, i|
      place = 10 ** (3 - i)
      convert_place(place, n.to_i)
    end.join
  end

  def convert_place(order_of_magnitude, n)
    raise ArgumentError, "order_of_magnitude does not have a Roman numeral equivalent: #{order_of_magnitude.inspect}" unless ROMAN_NUMERABLE_MAPPING.has_key?(order_of_magnitude)
    case n
    when 0, 1, 2, 3
      order_of_magnitude.roman_chr * n
    when 4
      order_of_magnitude.roman_chr + (order_of_magnitude * 5).roman_chr
    when 5, 6, 7, 8
      (order_of_magnitude * 5).roman_chr + (order_of_magnitude.roman_chr * (n - 5))
    when 9
      order_of_magnitude.roman_chr + (order_of_magnitude * 10).roman_chr
    else
      raise ArgumentError, "n is not an integer such that: 0 <= n <= 9: #{n.inspect}"
    end
  end

  def roman_chr
    ROMAN_NUMERABLE_MAPPING[self] or raise NoMethodError, "#roman_chr can only be called on: #{ROMAN_NUMERABLE_MAPPING.keys.join ', '}"
  end

end

class Integer
  include RomanNumerable
end
