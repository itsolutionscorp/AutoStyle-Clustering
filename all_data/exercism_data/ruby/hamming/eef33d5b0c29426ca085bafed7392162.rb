class Hamming
  	def self.compute(a, b)
  		a.split("").zip(b.split("")).count { |a| a[0] != a[1] && a[1] }
  	end
end
