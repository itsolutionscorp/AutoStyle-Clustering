class Bob
	
	def hey(message)
		return 'Sure.' if is_a_question? message.clone
		return 'Woah, chill out!' if is_a_yelling? message.clone
		return 'Fine. Be that way!' if is_nothing? message.clone
		return 'Whatever.'
	end

	def is_nothing?(msg)
		msg.strip.empty?
	end

	def is_a_yelling?(msg)
		unless is_nothing? msg 
			if contain_letters? msg
				msg.upcase!.nil?
			end
		end
	end

	def is_a_question?(msg)
		unless is_a_yelling?(msg)
			msg.gsub("\n","") =~ /\?$/
		end
	end

	private
	def contain_letters?(msg)
		msg =~ /[a-zA-Z]/
	end

end
