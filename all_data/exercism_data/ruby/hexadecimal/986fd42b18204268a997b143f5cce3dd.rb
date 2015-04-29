class Hexadecimal
  def initialize(str)
    @hex_digits = str.chars.map { |c| HexDigit.new(c) }
  end

  def to_decimal
    return 0 unless valid?
    hex_digits.reverse.map.with_index do |hex, i|
      16**i * hex.to_i
    end.inject(:+)
  end

  def valid?
    hex_digits.all?(&:valid?)
  end

  private
  attr_reader :hex_digits
end

class HexDigit
  attr_reader :char

  def initialize(char)
    @char = char
  end

  def to_i
    return char.to_i if ('0'..'9').include? char
    case char
    when 'a' then 10
    when 'b' then 11
    when 'c' then 12
    when 'd' then 13
    when 'e' then 14
    when 'f' then 15
    end
  end

  def valid?
    !to_i.nil?
  end
end
