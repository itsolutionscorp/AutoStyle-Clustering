class Bob
	def hey(text)
		
		return "Fine. Be that way!" if text.nil? or text.empty?
		return "Woah, chill out!" if text.upcase == text
		return "Sure." if text.end_with?('?')
		
		"Whatever."

	end
end
