module Complement

	def Complement.returnComplement(letter, dna)
		nucleotidesHash = {  
		"G" => "C", 
		"C" => "G", 
		"T" => "A",
		"A" => "U"  }
		unless dna==false
				nucleotidesHash[letter] 
			else nucleotidesHash.invert[letter]
		end
	end

	def Complement.of_dna(nucleotide) 
		a = Array.new
		for letter in 0..nucleotide.length-1
			a.push returnComplement(nucleotide[letter], true)
		end
		a.join	
	end

	def Complement.of_rna(nucleotide)
		a = Array.new
		for letter in 0..nucleotide.length-1
			a.push returnComplement(nucleotide[letter], false)
		end
		a.join	
	end
end
