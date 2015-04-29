## DNA/RNA Complement Builder
## Demannu 9/29/2014

class Complement
	def self.of_dna(sequence)
		arrseq = sequence.scan(/./)
		rna = ""
		arrseq.each do |single|
			case single
			when "G"
				rna << "C"
			when "C"
				rna << "G"
			when "T"
				rna << "A"
			when "A"
				rna << "U"
			end
		end
		return rna
	end

	def self.of_rna(sequence)
		arrseq = sequence.scan(/./)
		dna = ""
		arrseq.each do |single|
			case single
			when "C"
				dna << "G"
			when "G"
				dna << "C"
			when "A"
				dna << "T"
			when "U"
				dna << "A"
			end
		end
		return dna
	end
end
