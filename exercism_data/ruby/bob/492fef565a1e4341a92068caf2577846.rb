class Bob
	def initialize
	end
	def hey text
		text = text.strip
		return 'Fine. Be that way!' if empty? text
		return 'Woah, chill out!' if all_caps? text
		return 'Sure.' if question? text
		return 'Whatever.'
	end
	private
		def empty? text
			text == ' ' || text.empty? 
		end
		def question? text
			text.end_with? '?'
		end
		def all_caps? text
			all_caps = true
			text.each_char do |char|
				if ('a'..'z') === char then
					all_caps = false 
					break
				end
			end
			all_caps
		end
end 
