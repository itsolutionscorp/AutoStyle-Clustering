class Hamming
	def compute(a,b)
		a.split(//).zip(b.split(//)).count { |e| e[0] != e[1] }
	end
end
