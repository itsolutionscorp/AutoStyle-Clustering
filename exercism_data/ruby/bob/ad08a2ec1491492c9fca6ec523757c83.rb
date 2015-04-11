class Bob
	def initialize
	end

	def hey phrase
		question = 'Sure.'
		yell = 'Whoa, chill out!'
		fine = 'Fine. Be that way!'
		what = 'Whatever.'

		if phrase =~ /\.$/
			what
		elsif phrase =~ /\n/
			what
		elsif phrase =~ /.+\!.+\..+\?$/
			question
		elsif phrase =~ /[A-Z ]+\?$/
			yell
		elsif phrase =~ /\?/
			question
		elsif phrase =~ /\W+[\?$A-Z]+/
			yell
		elsif phrase =~ /\W+[\!$a-z]+/
			what
		elsif phrase =~ /Bob/
			fine
		elsif phrase =~ /[A-Z]/
			yell
		elsif phrase =~ /\d|^\?/
			what
		elsif phrase =~ / {3,}/
			fine
		elsif phrase == ""
			fine
		elsif phrase =~ /\t/
			fine
		end
	end
end
