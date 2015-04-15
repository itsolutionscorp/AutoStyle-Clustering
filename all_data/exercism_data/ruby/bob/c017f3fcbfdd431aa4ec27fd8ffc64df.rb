class Bob
	def hey(str)
		return "Fine. Be that way!" if str.nil? or str.strip.empty?
		return "Woah, chill out!" if str.upcase.eql?(str)
		return "Sure." if str.end_with?("?")
		"Whatever."
		end
end
