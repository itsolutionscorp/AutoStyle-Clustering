class Bob 

	def hey (greeting)
		
		response = "Whatever."

		if ( greeting == nil or greeting.strip == '')
			response = "Fine. Be that way."	
		elsif ( greeting.end_with?('?') )
			response = "Sure."
		elsif ( greeting.upcase! == nil )
			response = "Woah, chill out!"
		end
		
		response
	end

end
