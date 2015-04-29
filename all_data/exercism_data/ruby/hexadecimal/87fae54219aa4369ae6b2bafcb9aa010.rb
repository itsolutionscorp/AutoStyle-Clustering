class Hexadecimal

  def initialize(hex)
    @hex = hex
    @letters = %w(a b c d e f).zip([10, 11, 12, 13, 14, 15]).to_h
    @power = @hex.size - 1
  end

  def to_decimal
    return 0 if @hex.match(/[^abcdef][^\d]/)

    decimal = 0
    @hex.split('').each_with_index do |char, i|
      if @letters.keys.include?(char)
        decimal += @letters[char] * 16**(@power - i)
      else
        decimal += char.to_i * 16**(@power - i)
      end
    end
    decimal
  end

end
