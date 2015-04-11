
# * replace `G` -> `C`
# * replace `C` -> `G`
# * replace `T` -> `A`
# * replace `A` -> `U`

# * replace `G` -> `C`
# * replace `C` -> `G`
# * replace `U` -> `A`
# * replace `A` -> `T`


class Complement
	def self.of_dna(input)
		input.gsub(/[GCTA]/,'G'=>'C','C'=>'G','T'=>'A','A'=>'U')
	end

	def self.of_rna(input)
		input.gsub(/[GCUA]/,'G'=>'C','C'=>'G','U'=>'A','A'=>'T')
	end
end

#or enumerator each_char
