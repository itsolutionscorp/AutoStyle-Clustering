class SumOfMultiples
	
	def initialize(*multiples)
		@multiples = multiples
	end

	def self.to(n)
		new(3, 5).to(n)
	end

	def to(n)
		(1...n).select { |i| multiple? i }.reduce(0, :+)
	end

	private

		def multiple?(n)
			@multiples.any? do |m|
				n % m == 0
			end
		end

end
