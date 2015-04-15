class Trinary
  def initialize input
    @input = input
  end

  def to_decimal
    input.chars.reduce 0 do |sum, digit|
      sum * 3 + digit.to_i
    end
  end

  attr_reader :input
end
