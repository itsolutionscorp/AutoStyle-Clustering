class Bob
	
	def hey(message)
		return 'Sure.' if question? message
		return 'Woah, chill out!' if yelling? message
		return 'Fine. Be that way!' if nothing? message
		return 'Whatever.'
	end			

	def nothing?(msg)
		msg.strip.empty?
	end

	def yelling?(msg)
		unless is_nothing? msg 
			if contain_letters? msg
				msg.upcase.nil?
			end
		end
	end

	def question?(msg)
		unless is_a_yelling?(msg)
			msg.gsub("\n","") =~ /\?$/
		end
	end

	private
	def contain_letters?(msg)
		msg =~ /[a-zA-Z]/
	end

end
