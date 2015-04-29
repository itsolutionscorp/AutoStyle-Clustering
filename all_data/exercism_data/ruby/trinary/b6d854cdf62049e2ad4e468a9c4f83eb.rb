class Trinary
  def initialize(input)
    @input = input
  end

  def to_decimal
    @input.reverse.each_char.with_index.reduce(0) do |decimal, (digit, index)|
      decimal += digit.to_i * 3**index
    end
  end
end
