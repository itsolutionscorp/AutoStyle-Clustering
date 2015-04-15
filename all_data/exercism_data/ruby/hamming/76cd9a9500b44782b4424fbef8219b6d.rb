class Hamming
	def self.compute(a,b)
		tol = [a.length, b.length].min() -1
		sum = (0..tol).reduce(0) { |s,i|
			if (a[i]!=b[i])	
			then
				s += 1
			end
			s
		}
		
	end
end
