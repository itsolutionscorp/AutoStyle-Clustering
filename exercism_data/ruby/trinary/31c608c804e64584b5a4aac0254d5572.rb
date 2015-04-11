class Trinary

  attr_reader :trinary

  def initialize trinary
    @trinary = trinary
  end

  def to_decimal
    digits.each_with_index.reduce 0 do |decimal, (digit, index)|
      decimal += digit * (3 ** index)
    end
  end

private

  def digits
    trinary.match(/^[012]+$/).to_s.chars.reverse.map &:to_i
  end

end
