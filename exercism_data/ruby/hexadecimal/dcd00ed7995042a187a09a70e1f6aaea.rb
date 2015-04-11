class Hexadecimal
  attr_reader :hex

  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    powers_of_sixteen.each_with_index.map do |power, j|
      place = hex.chars.reverse.map{|c| normalize(c)}
      return 0 if place[j].nil?
      power * place[j]
    end.inject(&:+)
  end

  private

  def powers_of_sixteen
    powers = []
    hex.length.times do |i|
      powers << 16 ** i
    end
    powers
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
