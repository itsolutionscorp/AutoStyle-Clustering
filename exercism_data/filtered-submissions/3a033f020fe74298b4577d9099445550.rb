class Hamming

	def compute(dna_1, dna_2)
		diff = 0

		for i in 0..[dna_1.length - 1, dna_2.length - 1].min
			diff = (diff + 1) unless dna_1[i] == dna_2[i]
    end

    return diff
	end

end
