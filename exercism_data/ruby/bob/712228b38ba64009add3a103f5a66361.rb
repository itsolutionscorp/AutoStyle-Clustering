class Bob

	def hey(text)
		if !text.nil? && !text.empty?
			if text == text.upcase
				'Woah, chill out!'
			else
				if text.end_with?('.') || text.end_with?('!')
					'Whatever.'
				elsif text.include?('?') && !text.end_with?('?')
					'Whatever.'
				elsif text.end_with?('?')
					'Sure.'
				end
			end
		else
			'Fine. Be that way.'
		end
	end
end
