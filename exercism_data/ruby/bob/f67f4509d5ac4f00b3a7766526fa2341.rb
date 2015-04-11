class Bob

	def hey string

		string = string.to_s
		
		if string.upcase.eql?(string) && !string.empty?
			'Woah, chill out!'
		elsif string[-1].eql?('?')
			'Sure.'
		elsif string.empty?
			'Fine. Be that way.'
		else
			'Whatever.'
		end

	end


end
