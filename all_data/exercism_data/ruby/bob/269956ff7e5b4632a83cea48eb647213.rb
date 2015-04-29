class Bob
	def hey arg
		letters=arg.gsub(/[\W\d]/, '')
		if arg=='' || arg.squeeze==' '
			'Fine. Be that way!'
		elsif letters==letters.upcase &&letters.size>0
		  'Woah, chill out!'
		elsif arg.end_with? "?"
			"Sure."
		else
		  'Whatever.'
		end
	end
end
