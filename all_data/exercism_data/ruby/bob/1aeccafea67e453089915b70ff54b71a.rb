class Bob
	def hey(str)
		
		case 
			when str.delete(' ').empty? 
				return 'Fine. Be that way!'
			when str == str.upcase 
				return "Woah, chill out!"
			when str.end_with?("?")  
				return "Sure."
			else  
				return "Whatever."
		end
	end

end
