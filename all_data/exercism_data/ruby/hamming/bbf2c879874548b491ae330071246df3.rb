class Hamming
	def self.compute strand_a, strand_b
		if strand_a.empty? || strand_b.empty?
			0
		else
			self.compute_head(strand_a, strand_b) + self.compute_tail(strand_a, strand_b)
		end
	end

	def self.compute_head strand_a, strand_b
		(strand_a[0] == strand_b[0] ? 0 : 1)
	end

	def self.compute_tail strand_a, strand_b
		self.compute(strand_a[1..-1], strand_b[1..-1])
	end
end
