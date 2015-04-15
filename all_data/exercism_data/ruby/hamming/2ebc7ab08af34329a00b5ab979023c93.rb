class Hamming

	def self.compute(x, y)
		x.split(//).zip(y.split(//)).select{ |x| x[0] != x[1] }.count
	end
end
