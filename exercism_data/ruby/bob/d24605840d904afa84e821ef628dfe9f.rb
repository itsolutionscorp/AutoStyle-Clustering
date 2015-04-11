class Bob
	
	def hey(statement)

		case
		when statement !~ /\S/
			"Fine. Be that way!"
		when statement =~ /[A-Z]/ && statement !~ /[a-z]/
			"Whoa, chill out!"
		when statement =~ /\?\z/
			"Sure."
		else
			"Whatever."
		end
	end

end
