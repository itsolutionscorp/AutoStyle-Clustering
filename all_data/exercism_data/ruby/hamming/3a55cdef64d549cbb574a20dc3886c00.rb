class Hamming
	def self.compute(strand_a, strand_b)
		diff = 0	
		strand_a.size > strand_b.size ? (strand_a = strand_a[0...strand_b.size]) : (strand_b = strand_b[0...strand_a.size])
		strand_a.size.times { | idx | diff += 1 if strand_a[idx] != strand_b[idx] }
		diff
	end
end
