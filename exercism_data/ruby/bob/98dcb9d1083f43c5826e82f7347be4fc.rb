class Bob

	def hey string

		if empty?(string)
			'Fine. Be that way.'
		elsif yelling?(string)
			'Woah, chill out!'
		elsif question?(string)
			'Sure.'
		else
			'Whatever.'
		end

	end

	private

	def empty? string
			string.nil? || string.empty?
	end

	def yelling? string
		string.upcase == string && !string.empty?
	end

	def question? string
		string.end_with?('?')
	end

end
