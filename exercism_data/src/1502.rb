class Hamming
	class <<self
		def compute (strand_a, strand_b)
			position=0
			distance=0
			while (a=strand_a[position]) && (b = strand_b[position])
				distance = distance + 1 unless a==b
				position +=1
			end
			distance
		end
	end
end
