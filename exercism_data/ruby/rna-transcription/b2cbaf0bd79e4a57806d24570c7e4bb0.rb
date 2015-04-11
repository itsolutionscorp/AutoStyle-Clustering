class Complement

def self.of_dna (dnastring)
	dna = dnastring.split('')
	rna = []
	dna.each do |i|
		if (i.to_s == 'A')
			rna.push('T')
		elsif (i.to_s == 'T')
			rna.push('A')
		elsif (i.to_s == 'G')
			rna.push('C')
		elsif (i.to_s == 'C')
			rna.push('G')
		end
	end
	return rna
end

end
