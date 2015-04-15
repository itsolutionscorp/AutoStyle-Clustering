class Binary

 attr_accessor :num
	def initialize(num)
		@num = num
	end

	def to_decimal
	  if @num.to_i == 1
	  	return  1
	  else
	    @num.to_i(2)
	  end
	end
	

end
