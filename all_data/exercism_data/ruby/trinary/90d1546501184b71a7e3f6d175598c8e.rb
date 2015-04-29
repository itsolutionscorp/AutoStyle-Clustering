class Trinary

  def initialize(num)
  	@num = num.split("").reverse
  end

  def to_decimal
  	sum = 0
  	@num.each_with_index do |x,i|
  		sum += x.to_i*(3**i)
  	end
  	sum
  end

end
