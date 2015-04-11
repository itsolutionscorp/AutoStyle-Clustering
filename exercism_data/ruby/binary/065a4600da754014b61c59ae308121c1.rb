class Binary
  def initialize number
    @number = number
  end
  
  def binary_array
    @binary_array ||= @number.chars.map(&:to_i).reverse
  end
  
  def to_decimal
    @number =~ /^([0-1]+)$/  ? convert : 0
  end

  def convert
    binary_array.map.with_index{|value, index| 2 ** index * value }.inject(:+)
  end
end
