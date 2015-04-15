class Hamming

	def self.compute(x, y)

		x = x.chars
		y = y.chars

		x.zip(y).each.count { |x, y| x != y }

	end

end
