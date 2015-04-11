class Bob
	def hey(str)
	return 'Fine. Be that way!' if nothing?(str)
	return 'Woah, chill out!' if yelling?(str)
	return 'Sure.' if question?(str)
	'Whatever.'
	end

private
	def nothing?(str)
		str.to_s.strip.empty?
	end

	def yelling?(str)
		str == str.upcase
	end

	def question?(str)
		str.end_with? '?'
	end

end
