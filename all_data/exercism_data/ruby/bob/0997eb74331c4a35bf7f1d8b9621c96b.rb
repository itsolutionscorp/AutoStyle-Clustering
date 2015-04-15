class Bob
	def hey(text)
		if text.delete('^a-zA-Z') == text.delete('^a-zA-Z').upcase && !text.delete('^a-zA-Z').empty?
			response = 'Woah, chill out!'
		elsif text.split(//).last(1).join == "?"
			response = 'Sure.'		
		elsif text.strip.empty?
			response = 'Fine. Be that way!'
		else
			response = "Whatever."
		end

		response
	end
end
