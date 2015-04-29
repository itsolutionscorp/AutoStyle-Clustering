class Bob
	def hey(what_has_been_said)
		if silence?(what_has_been_said)
			"Fine. Be that way!"
		elsif shouting?(what_has_been_said)
			"Woah, chill out!"
		elsif asking?(what_has_been_said)
			"Sure."
		else
			"Whatever."
		end
	end

	def silence?(what_has_been_said)
		what_has_been_said.strip == ''
	end

	def shouting?(what_has_been_said)
		all_caps?(what_has_been_said) && contains_letters?(what_has_been_said)
	end

	def all_caps?(what_has_been_said)
		what_has_been_said.upcase == what_has_been_said
	end

	def contains_letters?(what_has_been_said)
		what_has_been_said =~ /[a-zA-Z]+/
	end

	def asking?(what_has_been_said)
		what_has_been_said.end_with?("?")
	end
end
