require 'pry'
class Binary

  def initialize(number)
    @number = number
  end

  def to_decimal
    if @number.to_f == 0.0
      return 0
    else
    @numbers = @number.to_s.scan(/./).reverse
    binary_num = 0
    @numbers.each_with_index do |num,index|
      binary_num += num.to_i * 2**index
    end
    binary_num
    end
  end

end
