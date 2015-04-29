#*****
#  Complement: Implement two methods of_dna and of_rna
#  
#  of_dna takes a string as an argument, references a hash
#  and replaces each character with its rna complement.
#  
#  of_rna inverses the hash and replaces each character with
#  its dna compliment
#*****

class Complement

	@nucleotide_complement = {
			'G' => 'C',
			'C' => 'G',
			'T' => 'A',
			'A' => 'U'
		}
	
	def self.of_dna(dna_nucleotide)
		strand = ''
		
		dna_nucleotide.each_char do | l |
			strand += "#{@nucleotide_complement[l]}"
		end
		strand
    end
	
	def self.of_rna(rna_nucleotide)
		#invert nucleotide_complement
		rna = @nucleotide_complement.invert
		strand = ''
	
		rna_nucleotide.each_char do | l |
		strand += "#{rna[l]}"
			
		end
		strand
	end
end
