class Complement

	DNA_TO_RNA_MAPPING = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U' }

	RNA_TO_DNA_MAPPING = DNA_TO_RNA_MAPPING.invert

	def self.convert_DNA_or_RNA(strand,mapping)
		strand.gsub(/./,mapping)
	end

	def self.of_dna(strand)
		convert_DNA_or_RNA(strand,DNA_TO_RNA_MAPPING)	
	end

	def self.of_rna(strand)
		convert_DNA_or_RNA(strand,RNA_TO_DNA_MAPPING)
	end
end


#puts "of_dna: " +  Complement.of_dna('ACGTGGTCTTAA') #UGCACCAGAAUU
#puts "of_rna: " + Complement.of_rna('UGCACCAGAAUU') #ACGTGGTCTTAA
