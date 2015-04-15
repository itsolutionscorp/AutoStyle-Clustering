class Binary
	def initialize(num)
		@num = num
		@i = -1
		@total = 0
	end
	def to_decimal
		if @num =~ /[[:alpha:]]/
			return 0
		else
			@num = @num.split("").reverse
			@num.each do |n|
				n = n.to_i
				@i += 1
				puts n
				@total += (n * 2**@i) 
			end
	
		return @total
	end

	end
end
