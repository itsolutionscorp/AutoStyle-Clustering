class Trinary
  attr_reader :num
  def initialize(num)
    @num = num
  end

  def to_decimal
    return 0 unless num.match(/^[012]+$/)
    num.reverse.chars.each_with_index.inject(0) do |decimal, (digit, index)|
      decimal += digit.to_i * (3**index)
    end
  end
end
