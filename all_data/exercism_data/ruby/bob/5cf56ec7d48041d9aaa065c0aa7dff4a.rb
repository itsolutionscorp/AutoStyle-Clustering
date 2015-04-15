class Bob
	def hey(frase) 
		case 
		when frase.end_with?("?")
			"Sure"
		when frase == frase.upcase
			"Woah, chill out!"
		when frase.empty?
			"Fine. Be that way!"
		else
			"Whatever."			
		end
	end
end
