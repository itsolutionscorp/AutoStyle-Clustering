class Hamming
		def self.compute (strand1, strand2)
			position=0
			Hamming=0
			while (a=strand1[position]) && (b = strand2[position])
				Hamming = Hamming+1 unless a==b
				position +=1
			end
			Hamming
		end
	end
end
