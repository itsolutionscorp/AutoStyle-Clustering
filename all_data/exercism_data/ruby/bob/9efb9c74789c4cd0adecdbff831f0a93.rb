#!/usr/bin/env ruby

class Bob
	def hey(msg)
		case msg
		when nil, '', /^\s+/
			'Fine. Be that way!'
		when msg.upcase
			'Woah, chill out!'
		when /\?$/
			'Sure.'
		else
			'Whatever.'
		end
	end
end
