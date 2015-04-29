class Hexadecimal
  DIGITS = '0123456789abcdef'

  def initialize(string)
    @string = string.downcase[/\A[#{DIGITS}]+\z|/]
  end

  def to_decimal
    @string.reverse.each_char.with_index.reduce(0) do |a, (c, i)|
      a + DIGITS.index(c) * 16**i
    end
  end
end
