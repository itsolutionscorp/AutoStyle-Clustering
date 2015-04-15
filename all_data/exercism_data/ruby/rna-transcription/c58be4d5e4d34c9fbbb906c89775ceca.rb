class Complement
	DNA_to_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	RNA_to_DNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
	
	def self.of_dna(strand)
		rna = ""
		strand.length.times{ |i|
			rna += DNA_to_RNA[strand[i]];
		}
		return rna
	end
	
	def self.of_rna(strand)
		dna = ""
		strand.length.times{ |i|
			dna += RNA_to_DNA[strand[i]];
		}
		return dna		
	end
end
