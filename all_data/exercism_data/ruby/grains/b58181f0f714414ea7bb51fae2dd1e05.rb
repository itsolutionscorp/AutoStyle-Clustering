class Grains

	@@chess = []
	(0...64).each do |x|
		@@chess << 2**x
	end

	def square(n)
		@@chess[n-1]
	end

	def total
		@@chess.inject(:+)
	end

end
