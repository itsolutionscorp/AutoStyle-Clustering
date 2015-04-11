class Complement
	def self.of_dna rna
		nucleo = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
		rna.gsub /[GCTA]/ do |match| 
			nucleo[match.to_s] 
		end
	end
	def self.of_rna dna
		nucleo = {'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T'}
		dna.gsub /[GCUA]/ do |match| 
			nucleo[match.to_s] 
		end
	end
end
