class Hamming
	def compute(strand_a, strand_b)
		diff = 0	
		strand_a.size > strand_b.size ? (strand_a = strand_a[0...strand_b.size]) : (strand_b = strand_b[0...strand_a.size])
		strand_a.size.times { | idx | diff += 1 if (strand_a[idx-1] != strand_b[idx-1]) }
		diff
	end
end
