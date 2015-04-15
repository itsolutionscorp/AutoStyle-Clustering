class Bob
	def hey arg
		
		if nothing? arg
			'Fine. Be that way!'
		elsif shout? arg
		  'Woah, chill out!'
		elsif arg.end_with? "?"
			"Sure."
		else
		  'Whatever.'
		end
	end
	
	private
	def shout? arg
		letters=arg.gsub(/[\W\d]/, '')
		letters==letters.upcase && letters.size>0
	end
	def nothing? arg
		arg=='' || arg.squeeze==' '
	end
	
end
