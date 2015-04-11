class Complement
	def self.of_dna(dna_strand)
		rna_strand = []

		dna_letters = dna_strand.split('').each do |x|
			case x
				when 'A' then rna_strand << 'U'
				when 'C' then rna_strand << 'G'
				when 'G' then rna_strand << 'C'
				when 'T' then rna_strand << 'A'
				else puts "An error occured."
			end
		end
		puts "The RNA compliment of the given DNA strand is: #{rna_strand.join('')}"
		return rna_strand.join('')
	end

	def self.of_rna(rna_strand)
		dna_strand = []

		rna_letters = rna_strand.split('').each do |x|
			case x
				when 'U' then dna_strand << 'A'
				when 'G' then dna_strand << 'C'
				when 'C' then dna_strand << 'G'
				when 'A' then dna_strand << 'T'
				else puts "An error occured."
			end
		end
		puts "The DNA compliment of the given RNA strand is: #{dna_strand.join('')}"
		return dna_strand.join('')
	end
end
