class Hamming
	def self.compute(a,b)
		if a.length == b.length
			similars = a.split(//).zip(b.split(//)).map {|x, y| x == y}
			return similars.count(false)
		else
			return 0
		end
	end
end
