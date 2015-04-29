class Bob
	def hey text
		if text.match(/[a-z]/).nil? && !text.match(/[A-Z]/).nil?
			'Whoa, chill out!'
		elsif text.end_with? '?'
			'Sure.'
		elsif text.strip.empty?
			'Fine. Be that way!'
		else
			'Whatever.'
		end
	end
end
