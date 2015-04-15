class Bob
	def initialize
	end
	def hey text
		text = text.strip
		return 'Fine. Be that way!' if text == ' ' || text.empty? 
		return 'Woah, chill out!' if all_caps text
		return 'Sure.' if text.end_with? '?'
		return 'Whatever.'
	end
	private
		def all_caps text
			all_caps = true
			text.each_char do |char|
				all_caps = false if ('a'..'z') === char
			end
			all_caps
		end
end
