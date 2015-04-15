class Complement

	def self.of_dna dna_strand
		rna = ""
		dna_strand.each_char do |n|
			rna << d_to_r(n)
		end
		rna
	end

	def self.of_rna rna_strand
		dna = ""
		rna_strand.each_char do |n|
			dna << r_to_d(n)
		end
		dna
	end

	def self.d_to_r n
		case n
		when 'G'
			'C'
		when 'C'
			'G'
		when 'T'
			'A'
		when 'A'
			'U'
		else
			raise "Funky mutation of nucleotide.."
		end

	end

	def self.r_to_d n
		case n
		when 'C'
			'G'
		when 'G'
			'C'
		when 'A'
			'T'
		when 'U'
			'A'
		else
			raise "Funky mutation of nucleotide.."
		end

	end

end
