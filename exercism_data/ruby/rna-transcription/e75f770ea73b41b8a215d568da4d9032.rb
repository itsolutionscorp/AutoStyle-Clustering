# gsub can take hashes! #MindBlown
class Complement
	class << self
		DNA = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=> 'U' }
		RNA = DNA.invert
		def of_dna(dna)
			dna.gsub( /[GCTA]/, DNA )
		end

		def of_rna(rna)
			rna.gsub( /[CGAU]/, RNA )
		end
	end
end # end class Complement
