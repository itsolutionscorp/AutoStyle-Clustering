class Hamming

	def self.compute(a,b)
		a,b = [a,b].sort { a.length <=> b.length }
		a.chars.zip(b.chars).reduce(0) do |dist, a|
			if a[0] != a[1]
				dist = dist + 1
			end
		dist
	end

end
