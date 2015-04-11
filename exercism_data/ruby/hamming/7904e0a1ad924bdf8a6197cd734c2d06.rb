class Hamming

	def self.trim_unequal_strings a, b
		if a.length == b.length
			return a, b
		else
			if a.length < b.length
				return a, b[0...a.length]
			else
				return b, a[0...b.length]
			end
		end
	end

	def self.compute a, b
		a, b = Hamming.trim_unequal_strings a, b
		if a.length && b.length == 1
			if a == b
				return 0
			else
				return 1
			end
		else
			return Hamming.compute(a.trunk, b.trunk) + Hamming.compute(a[-1], b[-1])
		end
	end
end

class String
	def trunk
		return self[0...self.length-1]
	end
end
