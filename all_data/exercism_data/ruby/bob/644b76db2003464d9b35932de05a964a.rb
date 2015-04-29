class Bob
	def hey(input)
		
		if input.nil? or input.strip.empty?
			return "Fine. Be that way!"
		end
	
		if input == input.upcase
			return "Woah, chill out!"
		end
		
		if input[-1] == '?'
			return "Sure."
		end
		
		"Whatever."
	end
end
