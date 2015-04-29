class Complement
	def self.of_dna(dna)
		computations = Computations.new
		computations.convert(dna, :swap_to_rna)
	end
	def self.of_rna(rna)
		computations = Computations.new
		computations.convert(rna, :swap_to_dna)
	end
		
end

class Computations
	def convert(genotype, func)
		@hashRNA = {"G" => "C", "C" => "G", "A" => "U", "T" => "A"}
		@hashDNA = {"C" => "G", "G" => "C", "U" => "A", "A" => "T"}

		zwrot = ""
		i =0;
		while(i < genotype.size)
			zwrot << method(func).call(genotype[i])
			i = i+1
		end
		zwrot
	end

	def swap_to_rna(nuc)
		@hashRNA[nuc]
	end

	def swap_to_dna(nuc)
		@hashDNA[nuc]
	end	
end
