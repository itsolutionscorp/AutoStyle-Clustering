class SumOfMultiples
	def initialize(*args)
		@multiples = args
	end

	def to(x)
		a = []
		@multiples.each do |m|
			e = m
			while e < x do
				a << e
				e += m
			end
		end
		a.empty? ? 0 : a.uniq.reduce(:+)
	end

	def self.to(x)
		new(3,5).to(x)
	end
end
