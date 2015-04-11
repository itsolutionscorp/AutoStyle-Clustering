class Bob
	
	@@loud     = /\p{Upper}/
	@@question = /\?\z/
	@@quiet    = /\p{Lower}/
	@@talk     = /\S/
	
	def hey (str)
		if @@talk.match(str)
			if @@loud.match(str) and !@@quiet.match(str)
				'Woah, chill out!'
			elsif @@question.match(str)
				'Sure.'
			else
				'Whatever.'
			end
		else
			'Fine. Be that way!'
		end
	end
	
end
