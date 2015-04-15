class Hamming

	def self.compute(dna1, dna2)
		dna1 = dna1.split("")
		dna2 = dna2.split("")
		difference(dna1, dna2)
	end

	private
	def self.difference(dna1, dna2)
		args = [dna1, dna2].sort{ |a, b| a.length <=> b.length }
		calc_diff(args[0], args[1])
	end

	def self.calc_diff(smaller_strand, dna2)
		counter = 0
		smaller_strand.each_with_index do |char, index|
			if char != dna2[index]
				counter += 1
			end
		end
		counter
	end
end
