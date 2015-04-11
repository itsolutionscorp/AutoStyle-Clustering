class Complement
	def self.of_dna str 
		# str.length.times do |i|
		# 	case str[i]
		# 	when 'G'
		# 		str[i] = 'C'
		# 	when 'C'
		# 		str[i] = 'G'
		# 	when 'T'
		# 		str[i] = 'A'
		# 	when 'A'
		# 		str[i] = 'U'
		# 	end
		# end
		
		# str	
		str.tr!('GCTA', 'CGAU')
	end


	def self.of_rna str
		str.length.times do |i|
			case str[i]
			when 'C'
				str[i] = 'G'
			when 'G'
				str[i] = 'C'
			when 'U'
				str[i] = 'A'
			when 'A'
				str[i] = 'T'
			end
		end
		str
	end

end
