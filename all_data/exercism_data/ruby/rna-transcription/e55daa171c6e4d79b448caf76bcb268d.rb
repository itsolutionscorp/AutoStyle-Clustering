class Complement
	DNA_to_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	RNA_to_DNA = DNA_to_RNA.invert
	
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
