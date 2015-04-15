class Complement
	def self.of_dna(str)
		for i in 0...str.length
			if str[i] == 'G'
				str[i] = 'C'
			elsif str[i] == 'C'
				str[i] = 'G'
			elsif str[i] == 'T'
				str[i] = 'A'
			elsif str[i] == 'A'
				str[i] = 'U'
			end
		end 

		return str
	end

	def self.of_rna(str)
		for i in 0...str.length
			if str[i] == 'G'
				str[i] = 'C'
			elsif str[i] == 'C'
				str[i] = 'G'
			elsif str[i] == 'U'
				str[i] = 'A'
			elsif str[i] == 'A'
				str[i] = 'T'
			end
		end

		return str
	end
end

#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`
