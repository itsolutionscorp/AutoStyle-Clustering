class Bob

	def hey string

		string = string.to_s

		if yelling?(string)
			'Woah, chill out!'
		elsif question?(string)
			'Sure.'
		elsif string.empty?
			'Fine. Be that way.'
		else
			'Whatever.'
		end

	end

	private
	
	def yelling? string
		if string.upcase == string && !string.empty?
			true
		end
	end

	def question? string
		if string.end_with?('?')
			true
		end
	end

end
