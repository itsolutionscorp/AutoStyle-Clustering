class Trinary
  attr_reader :digits

  def initialize(string)
    @digits = string.match(/^\d+$/) ? string.chars.map(&:to_i) : []
  end

  def to_decimal
    digits.reverse.each_with_index.map do |digit, index|
      digit * (3 ** index)
    end.inject(0, :+)
  end
end
