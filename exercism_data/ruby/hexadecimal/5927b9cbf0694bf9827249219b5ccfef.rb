class Hexadecimal
  attr_reader :hex

  def initialize(hex)
    @hex = parse(hex)
  end

  def to_decimal
    hex.each_with_index.inject(0) do |sum, (digit, i)|
      return 0 if digit.nil?
      sum + digit * (16 ** i)
    end
  end

  private

  def parse(hexadecimal)
    hexadecimal.chars.reverse.map{|c| normalize(c)}
  end

  def normalize(hexadecimal)
    hexadecimal.match(/\d+/) ? hexadecimal.to_i : conversions[hexadecimal]
  end

  def conversions
    { "a" => 10,
      "b" => 11,
      "c" => 12,
      "d" => 13,
      "e" => 14,
      "f" => 15
    }
  end

end
