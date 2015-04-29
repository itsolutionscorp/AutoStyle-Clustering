class Hexadecimal
  COIEFFICIENTS = "0123456789abcdef"

  def initialize(hexadecimal)
    @hexadecimal = hexadecimal
  end

  def to_decimal
    return 0 unless valid?

    hex_digits_at_index.reduce(0) do |decimal, (digit, index)|
      decimal + (16**index * COIEFFICIENTS.index(digit))
    end
  end

  private

  def hex_digits_at_index
    @hexadecimal.reverse.chars.each_with_index
  end

  def valid?
    @hexadecimal.tr(COIEFFICIENTS, '').empty?
  end
end
