class Hexadecimal

  HEXADECIMAL = [*0..9].map(&:to_s) + [*'a'..'f']
  DECIMAL     = [*0..15]
  CONVERSIONS = Hash[HEXADECIMAL.zip DECIMAL]

  def initialize(hex)
    @hex = invalid?(hex) ? "0" : hex
  end

  def to_decimal
    @hex.reverse.chars.each_with_index.inject(0) do |sum, (digit,i)|
      sum + CONVERSIONS[digit] * 16**i
    end
  end

  private

  def invalid?(hex)
    # check if any digit not eql to hexadecimal
    hex[/[^\da-f]/]
  end
end
