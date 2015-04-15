class Hamming
	def self.compute(a, b)
		if (a.empty? || b.empty?)
			0
		else
			self.compute_head(a, b) + self.compute_tail(a, b)
		end
	end

	def self.compute_head(a, b)
		(a[0] == b[0] ? 0 : 1)
	end

	def self.compute_tail(a, b)
		self.compute(a[1..-1], b[1..-1])
	end
end
