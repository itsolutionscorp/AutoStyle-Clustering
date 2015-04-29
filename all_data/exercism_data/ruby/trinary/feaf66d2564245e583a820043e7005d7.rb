class Trinary

  def initialize(string)
    @string = string.reverse
  end

  def to_decimal
    @string.each_char.with_index.reduce(0) { |sum, (char, index)| sum + char.to_i * 3 ** index }
  end
end
