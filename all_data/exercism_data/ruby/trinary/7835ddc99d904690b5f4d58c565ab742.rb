class Trinary
  def initialize(number)
    @number = number
  end

  def to_decimal
    @number.reverse.chars.each_with_index.inject(0) do |decimal, (digit, index)|
      decimal + digit.to_i * (3**index)
    end
  end
end
