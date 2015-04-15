class Bob
	def hey(input)
		if (input == input.upcase) && (input.match(/[A-Z]/))
			return "Woah, chill out!"
		end
		if(input[-1, 1] == "?" )
			return "Sure."
		end
		if(input.match( /\A\s*\Z/))
			return "Fine. Be that way!"
		else
			return "Whatever."
		end
	end
end
