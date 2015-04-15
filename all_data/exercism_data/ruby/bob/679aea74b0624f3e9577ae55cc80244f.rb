class Bob
	def hey(input)
		contains_num = has_num(input)
		if input.gsub(/\s+/, "").empty?
			'Fine. Be that way!'
		elsif (input == input.upcase && !contains_num) || (input[-1] == '!' && contains_num)
			'Woah, chill out!'
		elsif (input[-1] == '?' && !contains_num) || (input =~ /\d/ && input[-1] == '?')
			'Sure.'
		else
			"Whatever."
		end
	end

	def has_num(s)
		s.each_char do |c|
			return true if c =~ /\d/
		end
		return false
	end
end
