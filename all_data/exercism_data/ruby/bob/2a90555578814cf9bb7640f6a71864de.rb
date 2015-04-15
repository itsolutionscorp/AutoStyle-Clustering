class Bob
	def hey(prompt)
		if prompt.upcase==prompt && has_letters?(prompt) 
			"Woah, chill out!"
		elsif prompt[-1]=="?"
			'Sure.'
		elsif prompt.gsub(" ","")==""
			'Fine. Be that way!'
		else
			"Whatever."
		end
		
	end
	

private

	def has_letters?(string)
		string.match(/[a-zA-Z]/) != nil
	end
	
end
