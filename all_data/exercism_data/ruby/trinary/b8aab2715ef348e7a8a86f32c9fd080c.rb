class Trinary
  def initialize(num)
    @num = num
    @exponent = num.size - 1
  end

  def to_decimal
    @num.split('').each_with_index.inject(0) do |result, (digit, i)|
      result += digit.to_i * 3**(@exponent - i)
      result
    end
  end
end
