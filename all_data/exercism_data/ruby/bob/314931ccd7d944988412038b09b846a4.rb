class Bob
	def hey sentence
		if sentence.gsub(/\s+/, "") == "" then
			return 'Fine. Be that way!'
		elsif sentence.upcase == sentence && /[A-Z]/ === sentence then
			return 'Woah, chill out!'
		elsif sentence[sentence.length-1] == '?' then
			return 'Sure.' 
		else
			return 'Whatever.'
		end
	end
end
