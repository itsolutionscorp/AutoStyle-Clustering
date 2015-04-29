class Bob
	def hey(str)
		return "Fine. Be that way." if str.nil? or str.empty?
		return "Sure." if str.end_with?("?")
		return "Woah, chill out!" if str.upcase == str
		"Whatever."
	end
end
