class SumOfMultiples
	
	def initialize(*multiples)
		@multiples = multiples
	end

	def self.to(n)
		SumOfMultiples.new(3, 5).to(n)
	end

	def to(n)
		(1...n).select do |i|
			@multiples.any? do |m|
				i % m == 0
			end
		end.reduce(0, :+)
	end

end

# puts SumOfMultiples.to(1)
