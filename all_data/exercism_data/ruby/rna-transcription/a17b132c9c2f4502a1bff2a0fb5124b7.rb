require 'pry'

class Complement
	@@DNAtoRNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	@@RNAtoDNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

	def self.of_dna(rna)
		dna = ''
		rna.each_char do |base|
			dna += @@DNAtoRNA[base]
		end
		return dna
	end

	def self.of_rna(dna)
		rna = ''
		dna.each_char do |base|
			rna += @@RNAtoDNA[base]
		end
		return rna
	end
end
