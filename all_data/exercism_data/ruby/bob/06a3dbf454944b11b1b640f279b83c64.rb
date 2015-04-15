class Bob
	def hey(input)
		@input = input.to_s.strip
		return "Fine. Be that way!" if @input.empty?
		return "Whoa, chill out!" if @input =~ /[A-Z]/ && @input.upcase == @input
		return "Sure." if @input.end_with?('?')
		return "Whatever."
	end
end

