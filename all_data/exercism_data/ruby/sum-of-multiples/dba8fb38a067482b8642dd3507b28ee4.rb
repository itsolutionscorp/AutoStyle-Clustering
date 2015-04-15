class SumOfMultiples

	def initialize(*multiples)
		@multiples = multiples
	end

	def to(limit)
		sum = 0
		(0...limit).each do |n|
			sum += n if @multiples.any? { |multiple| n % multiple == 0 }
		end
		sum
	end

	def self.to(limit)
		new(3, 5).to(limit)
	end

end
