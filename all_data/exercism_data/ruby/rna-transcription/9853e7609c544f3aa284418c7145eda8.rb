class Complement

	def self.of_dna(rna)
		i=0
		dna=""
		while i<rna.length
			if rna[i]=="C"
				dna=dna+"G"
			elsif rna[i]=="G"
				dna=dna+"C"
			elsif rna[i]=="T"
				dna=dna+"A"
			else
				dna=dna+"U"
			end
			
			i=i+1
		end
		dna
	end
	
	def self.of_rna(dna)
		i=0
		rna=""
		while i<dna.length
			if dna[i]=="C"
				rna=rna+"G"
			elsif dna[i]=="G"
				rna=rna+"C"
			elsif dna[i]=="U"
				rna=rna+"A"
			else
				rna=rna+"T"
			end
			
			i=i+1
		end
		rna
	end
	
end
