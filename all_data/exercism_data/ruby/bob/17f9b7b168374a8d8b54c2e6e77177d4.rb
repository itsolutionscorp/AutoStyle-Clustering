class Bob
	def hey(phrase)
		if silent?(phrase)
			'Fine. Be that way!'
		elsif shouting?(phrase)
			'Woah, chill out!'
		elsif question?(phrase)
			'Sure.'
		else
			'Whatever.'
		end
	end

	private

	def silent?(phrase)
		phrase == '' || phrase.nil?
	end

	def shouting?(phrase)
		phrase =~ /\A[A-Z\d\W]+\Z/
	end

	def question?(phrase)
		phrase =~ /\?\Z/
	end
end
