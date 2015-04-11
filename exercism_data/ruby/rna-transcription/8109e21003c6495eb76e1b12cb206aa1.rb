=begin
  File: complement.rb
  Author: sherinom
=end

class Complement

	def self.of_dna(dna_strand)
		
		rna_strand = ''
		
		dna_strand.upcase.chars.each do |c|
			case c
			when 'A'
				rna_strand += 'U'
			when 'C'
				rna_strand += 'G'
			when 'G'
				rna_strand += 'C'
			when 'T'
				rna_strand += 'A'
			end
		end
		
		return rna_strand
		
	end

	def self.of_rna(rna_strand)
		
		dna_strand = ''
		
		rna_strand.upcase.chars.each do |c|
			case c
			when 'A'
				dna_strand += 'T'
			when 'C'
				dna_strand += 'G'
			when 'G'
				dna_strand += 'C'
			when 'U'
				dna_strand += 'A'
			end
		end
		
		return dna_strand
		
	end
	
end
