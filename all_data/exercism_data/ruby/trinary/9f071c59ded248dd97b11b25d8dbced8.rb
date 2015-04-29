class Trinary
  def initialize(number)
    @number = number.chars.map(&:to_i)
  end

  def to_decimal
    @number.each_with_index.inject(0) do |sum, (item, index)|
      sum += item * 3 ** (@number.length - index - 1)
    end
  end
  
end
