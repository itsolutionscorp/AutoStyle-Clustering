class Trinary
  attr_reader :num

  def initialize(num)
    @num = num.to_i(3)
  end

  def to_decimal
    num
  end
end
