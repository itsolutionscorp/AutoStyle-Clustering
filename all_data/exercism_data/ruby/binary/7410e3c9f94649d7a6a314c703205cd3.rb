class Binary
  
  def initialize(num)
    @num = num.split("").reverse
  end

  def to_decimal
  	sum = 0
    @num.each_with_index do |x,i|
      return 0 if x =~ /[a-zA-Z]/
      sum += (x.to_i * 2**i)
    end
    sum
  end

end
