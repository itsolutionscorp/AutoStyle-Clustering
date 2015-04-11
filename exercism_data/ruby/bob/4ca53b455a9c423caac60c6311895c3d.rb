class Bob
	def hey(statement)
		case
		when statement.match(/\A\s*\z/)
			'Fine. Be that way!'
		when statement.match(/^[^a-z]*\z/) && statement.match(/[A-Z]/)
			'Whoa, chill out!'
		when statement.match(/\?\z/)
			'Sure.'
		else
			'Whatever.'
		end
	end
end
