class Complement
	def self.of_dna(str)
		i = 0
		while str[i]
			if str[i] == 'C'
				str[i] = 'G'
			elsif str[i] == 'G'
				str[i] = 'C'
			elsif str[i] == 'T'
				str[i] = 'A'
			elsif str[i] == 'A'
				str[i] = 'U'
			end
			i+= 1
		end
		return str
	end
	def self.of_rna(str)
		i = 0
		while str[i]
			if str[i] == 'C'
				str[i] = 'G'
			elsif str[i] == 'G'
				str[i] = 'C'
			elsif str[i] == 'U'
				str[i] = 'A'
			elsif str[i] == 'A'
				str[i] = 'T'
			end
			i+= 1
		end
		return str
	end
end
