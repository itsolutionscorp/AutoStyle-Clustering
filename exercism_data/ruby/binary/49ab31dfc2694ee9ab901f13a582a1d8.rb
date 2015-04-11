class Binary
  attr_reader :to_decimal
  
  def initialize(binary)
     @to_decimal = Array.new
    binary.scan(/./).reverse.each_with_index.map{|digit, ndx| break if digit =~ /[^01]/   
          @to_decimal[ndx] = digit.to_i * (2**ndx)
    }
    @to_decimal = @to_decimal.inject(0, :+)
  end
  
end
