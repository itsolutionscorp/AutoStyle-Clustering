class Trinary

  VALID_FORMAT = /^[012]+$/

  def initialize(string)
    @string = string.reverse
  end

  def to_decimal
    return 0 unless @string.match(VALID_FORMAT)
    @string.each_char.with_index.reduce(0) { |sum, (char, index)| sum + char.to_i * 3 ** index }
  end
end
