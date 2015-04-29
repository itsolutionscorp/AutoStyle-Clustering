class Hexadecimal
  attr_reader :num

  def initialize num
    @num = num
  end

  def to_decimal
    return 0 if invalid_hex?

    num.chars.reduce(0) { |dec, n| dec * 16 + char2dec(n) }
  end

  private

  def invalid_hex?
    !!num.gsub(/[0-9]/, '').match(/[^abcdef]/)
  end

  def char2dec char
    {
      'a' => 10, 'b' => 11, 'c' => 12,
      'd' => 13, 'e' => 14, 'f' => 15
    }.fetch(char) { char.to_i }
  end
end
