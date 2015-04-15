# gsub can take hashes! #MindBlown
class Complement
	class << self
		def of_dna(dna)
			dna.gsub( /[GCTA]/, 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=> 'U' )
		end

		def of_rna(rna)
			rna.gsub( /[CGAU]/, 'C'=>'G', 'G'=>'C', 'A'=>'T', 'U'=>'A' )
		end
	end
end # end class Complement
