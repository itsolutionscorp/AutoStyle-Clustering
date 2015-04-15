class Bob
	
	@@loud     = /\p{Upper}/
	@@question = /\?\z/
	@@quiet    = /\p{Lower}/
	@@silence  = /\S/
	
	def hey (str)
		if !@@silence.match(str)
			'Fine. Be that way!'
		elsif @@loud.match(str) and !@@quiet.match(str)
			'Woah, chill out!'
		elsif @@question.match(str)
			'Sure.'
		else
			'Whatever.'
		end
	end
	
end
