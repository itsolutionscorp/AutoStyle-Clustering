class Bob

	def hey(something)		
	
		case something
			when /\A\s*\Z/
				'Fine. Be that way!'
			when /[a-z0-9]+.*[?]+\Z/
				'Sure.' 
			when /\A[^a-zA-Z]+\Z/
				'Whatever.'
			when something.upcase 
				'Woah, chill out!'
			else
				'Whatever.'
		end
		
	end

end


