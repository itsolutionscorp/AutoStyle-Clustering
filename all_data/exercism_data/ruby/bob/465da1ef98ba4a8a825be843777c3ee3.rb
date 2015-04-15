class Bob
	def hey(something)
		return "Fine. Be that way." if !something || something.length == 0
		return "Woah, chill out!" if !something.match(/[a-z]/)
		return "Sure." if something.end_with?("?")
		return "Whatever."
	end
end
