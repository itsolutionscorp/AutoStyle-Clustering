class Hamming
	def self.compute(a,b)
		return (0...[a.length,b.length].min).count{|i| a[i]!=b[i]}
	end
end
