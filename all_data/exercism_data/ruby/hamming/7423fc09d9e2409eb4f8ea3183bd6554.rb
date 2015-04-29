class Hamming
	def self.compute(a,b)
		a = a[0,b.length].split("")
		b = b[0,a.length].split("")
		(a.zip(b).map {|a,b| a == b }).count(false)
	end
end
