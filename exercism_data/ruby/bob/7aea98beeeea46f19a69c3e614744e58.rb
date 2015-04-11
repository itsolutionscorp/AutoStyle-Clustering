class String
end

class Bob
	def hey(text)
		return 'Fine. Be that way!' if text.nil? || text.strip.empty?
		return 'Woah, chill out!' if text == text.upcase
		return 'Sure.' if text =~ /\?\Z/
		return 'Whatever.'
	end
end
