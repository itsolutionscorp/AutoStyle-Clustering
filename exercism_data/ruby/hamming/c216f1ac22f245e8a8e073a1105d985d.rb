class Hamming

	def self.compute(a,b,dist=0)
		short, long = [a.to_s, b.to_s].sort
		ms = short.size - 1
		long[0..ms].chars
		.zip(short.chars)
		.each { |ac, bc|
			dist += 1 if ac != bc
		}
		dist
	end

end
