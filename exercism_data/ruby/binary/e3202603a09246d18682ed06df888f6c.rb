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
    response = 0
    binary_array.each_with_index do |value, index|
      response +=  2 ** index * value
    end
    response
  end
end
