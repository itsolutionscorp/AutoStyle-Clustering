require 'pry-byebug'

class Binary
  def initialize(binary_num)
    @binary_num = binary_num
  end

  def to_decimal
    if @binary_num.gsub(/\D/, "*" ).include? "*"
      return 0
    else
    exponent = (@binary_num.length) -1
    decimal = [] 
    
    until exponent == -1 do
    @binary_num[0].each_char { |num|  decimal << ((num.to_i) * ( 2 ** exponent )) }
      exponent -= 1 
      @binary_num[0] = ""
    end
    end
    decimal.inject { |mem, x| mem + x }
 end
end
