class Bob
	def hey(statement)
		if says_nothing?(statement)
			"Fine. Be that way!"
		elsif shouting?(statement)
			"Woah, chill out!"
		elsif ends_with_question?(statement)
			"Sure."
		else
			"Whatever."
		end
	end

	private
	def says_nothing?(input)
		input.gsub(/\s/,'').empty?
	end

	def shouting?(input)
		input == input.upcase
	end

	def ends_with_question?(input)
		input[-1] == "?"
	end
end
