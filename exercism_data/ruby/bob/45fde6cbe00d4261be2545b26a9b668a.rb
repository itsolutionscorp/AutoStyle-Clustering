# Default behaviour
class Parser
	def fits?(msg)
		true
	end
end	

class Dumb < Parser
	def fits?(msg)
		msg.strip.empty?
	end

	def respond
		'Fine. Be that way!'
	end
end

class Shouting < Parser
	def fits?(msg)
		msg == msg.upcase
	end

	def respond
		'Woah, chill out!'
	end
end

class Questions < Parser
	def fits?(msg)
		msg.end_with? '?'
	end

	def respond
		'Sure.'
	end
end

class Anything < Parser
	def respond
		'Whatever.'
	end
end

class Bob
	Parsers = [Dumb, Shouting, Questions, Anything]
	
	def hey(msg)
		Parsers.map(&:new).each do |parser|
			return parser.respond if parser.fits? msg
		end
	end
end
