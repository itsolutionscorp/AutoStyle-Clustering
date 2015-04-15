class Trinary
  def initialize(input)
    @n = input
  end

  def to_decimal
    @n.reverse.chars.each_with_index.inject(0) do |result,(num, index)|
      result + num.to_i * 3**index
    end
  end
end
