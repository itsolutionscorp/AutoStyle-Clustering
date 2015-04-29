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
		string.upcase == string && !string.empty?
	end

	def question? string
		string.end_with?('?')
	end

end
