class Hamming
	def self.compute(a,b)
		len = [a.length, b.length].min
		(0...len).count do |i|
			a[i] != b[i]
		end
	end
end
