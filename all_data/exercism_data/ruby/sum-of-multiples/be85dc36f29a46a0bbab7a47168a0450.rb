class SumOfMultiples
	$multiples = [3, 5]

	def initialize(*args)
		$multiples = args
	end

	def self.to(x)
		a = []
		$multiples.each do |m|
			e = m
			while e < x do
				a << e
				e += m
			end
		end
		a.empty? ? 0 : a.reduce(:+)
	end

	def to(x)
		self.class.to(x)
	end
end
