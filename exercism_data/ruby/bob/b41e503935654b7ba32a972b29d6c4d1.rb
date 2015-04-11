class Bob
	def hey(statement)
		str = remove_nums(statement)
		if str == str.upcase && contains_letters?(str)
			"Woah, chill out!"
		elsif str[-1] == '?'
			"Sure."
		elsif str.lstrip.empty?
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end
	
	private
	
	def remove_nums(statement)
		statement.sub(/[0-9]/, " ")
	end

	def contains_letters?(statement)
		statement.downcase =~ /[a-z]/
	end
end
