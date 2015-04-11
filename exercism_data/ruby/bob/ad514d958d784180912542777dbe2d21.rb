class Bob
	def hey(ask)
		if ask.strip.empty?
			return 'Fine. Be that way!'
		end

		if ask == ask.upcase
			return 'Woah, chill out!'
		end

		if ask.chars.last == '?'
			return 'Sure.'
		end

		"Whatever."
	end
end
