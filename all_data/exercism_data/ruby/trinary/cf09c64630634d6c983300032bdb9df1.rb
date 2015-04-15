class Trinary
  attr_reader :digits

  def initialize(string)
    @digits = string.match(/^\d+$/) ? string.chars.map(&:to_i) : []
  end

  def to_decimal
    digits.reverse.each_with_index.inject(0) do |result, (digit, index)|
      result + digit * (3 ** index)
    end
  end
end
