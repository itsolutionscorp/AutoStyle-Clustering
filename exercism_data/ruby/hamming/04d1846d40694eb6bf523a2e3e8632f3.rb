class Hamming
	def self.compute(d1,d2)
		n = 0
		i = 0
		d1 = d1.split("")
		d2 = d2.split("")

		while i < d1.length
			n += match(d1[i],d2[i])
			i += 1
		end

		return n
	end

	def self.match(a,b)
		if a == b
			return 0
		else
			return 1
		end
	end
end
