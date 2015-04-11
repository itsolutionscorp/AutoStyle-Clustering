class Complement 
	def self.of_dna(dna)
		dna_comp = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" };
		finalcount=0;
		rna="";
  	dna.length.times do |i| 
		   rna=rna+dna_comp[dna[i]];
		end
  	return rna;
	end
	def self.of_rna(rna)
		rna_comp = { "C" => "G", "G" => "C", "A" => "T", "U" => "A" };
		finalcount=0;
		dna="";
  	rna.length.times do |i| 
		   dna=dna+rna_comp[rna[i]];
		end
  	return dna;
	end
end
