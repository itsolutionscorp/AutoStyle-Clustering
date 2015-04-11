class Series
	def initialize(num)
		@num = num
		@len = num.length
	end
	def slices(len)
		# @num.split("").map { |e| [e.to_i] }
		if len > @len
			raise ArgumentError
		end
		it = 0
		slices = []
		while it+len <= @len
			slices << @num[it,len].split("").map { |e| e.to_i }
			it+=1
		end
		slices
	end
end

