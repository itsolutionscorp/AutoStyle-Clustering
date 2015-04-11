class Bob

	def hey(words)
		if words[-1] == '?'
			'Sure.'
		else 
			if words.upcase == words
				'Woah, chill out!'
			else
				if words[/\w+/]
					'Whatever.'
				else
					'Fine. Be that way!'
				end
			end
		end

	end

end
