class Bob
	def is_question(prompt)
		return false if is_shout(prompt)
		prompt[-1] == "?"
	end

	def is_blank(prompt)
		prompt.strip.empty?
	end

	def is_shout(prompt)
		contains_lowercase = prompt =~ /[a-z]/
		contains_uppercase = prompt =~ /[A-Z]/
		contains_uppercase and not contains_lowercase 
	end

	def hey(prompt)
		response = case 
		when is_blank(prompt)
			"Fine. Be that way!"
		when is_question(prompt)
			"Sure."
		when is_shout(prompt)
			"Woah, chill out!"
		else
			"Whatever."
		end
	end
end
