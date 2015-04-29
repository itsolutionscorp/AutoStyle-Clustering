class Bob
	def hey(what)
		if is_nothing?(what)
			FINE
		elsif is_question?(what)
			SURE
		elsif is_yelling?(what)
			WOAH
		else
			WHATEVER
		end
	end

	protected
	def is_question?(what)
		what.end_with? "?"
	end

	def is_nothing?(what)
		what.nil? or what == ""
	end

	def is_yelling?(what)
		what.upcase == what
	end

	WHATEVER = 'Whatever.'
	SURE = 'Sure.'
	FINE = 'Fine. Be that way.'
	WOAH = 'Woah, chill out!'

	[WHATEVER, SURE, FINE, WOAH].each &:freeze
end
