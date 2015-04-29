class Complement
	DNA_RNA_COMPLEMENT = {'G'=> 'C', 'C'=> 'G', 'T'=> 'A', 'A'=> 'U'}
	class << self
		def of_dna(dna)
			dna.split('').collect {|x| DNA_RNA_COMPLEMENT[x]}.join
		end

		def of_rna(rna)
			rna.split('').collect {|x| DNA_RNA_COMPLEMENT.invert[x]}.join
		end
	end
end
