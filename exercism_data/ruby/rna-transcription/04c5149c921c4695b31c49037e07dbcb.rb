DNA_HASH = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
RNA_HASH = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

class Complement
	def self.of_dna(rna)
		rna.split('').map{ |d| DNA_HASH[d]}.join
	end

	def self.of_rna(dna)
		dna.split('').map{ |r| RNA_HASH[r]}.join
	end
end
