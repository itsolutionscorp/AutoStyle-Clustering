class Grains
  def initialize
  end

  def square(nth)
  	nth == 1 ? 1 : 2**(nth-1)
  end

  def total
  	@total = 1
  	63.times { |i| 
  		       i += 1 
  		       @total += 2**i 
  		     }
  	@total
  end
end
