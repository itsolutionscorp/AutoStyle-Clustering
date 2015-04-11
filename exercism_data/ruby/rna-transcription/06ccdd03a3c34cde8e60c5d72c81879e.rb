class Complement

	def self.of_dna(dna)
		dnaarr=dna.split('')
		rnaarr=[]
		dnaarr.each do |letter|
			if letter=='G'
				rnaarr.push('C')
			elsif letter=='C'
				rnaarr.push('G')
			elsif letter=='T'
				rnaarr.push('A')
			else 
				rnaarr.push('U')
			end
		end
		rnaarr.join
	end

	def self.of_rna(rna)
			rnaarr=rna.split('')
		dnaarr=[]
		rnaarr.each do |letter|
			if letter=='G'
				dnaarr.push('C')
			elsif letter=='C'
				dnaarr.push('G')
			elsif letter=='U'
				dnaarr.push('A')
			else 
				dnaarr.push('T')
			end
		end
		dnaarr.join
	end

end
