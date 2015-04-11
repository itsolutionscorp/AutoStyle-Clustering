class Bob
	def hey (greeting)
		return "Fine. Be that way." if ignoring? greeting
		return "Woah, chill out!" if shouting? greeting
		return "Sure." if asking? greeting
		"Whatever."
	end
	def asking? (greeting)
		greeting.end_with? '?'
	end
	def ignoring? (greeting)
		greeting.nil? || greeting.empty?
	end
	def shouting? (greeting)
		greeting.upcase == greeting
	end
end
