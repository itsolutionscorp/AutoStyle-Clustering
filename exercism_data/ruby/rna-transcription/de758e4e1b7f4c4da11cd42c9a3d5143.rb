class Complement
	def self.of_rna(strand)
		result = String.new
		strand.each_char do |c|
			case c
				when 'G'
					result += 'C'
				when 'C'
					result += 'G'
				when 'T'
					result += 'A'
				when 'A'
					result += 'U'
				else 
					raise 'Bad strand'
			end	
		end
		result
	end
end
