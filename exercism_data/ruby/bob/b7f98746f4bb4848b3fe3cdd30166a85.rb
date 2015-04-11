class Dumb
	def match?(msg)
		msg.strip.empty?
	end

	def respond
		'Fine. Be that way!'
	end
end

class Shouting
	def match?(msg)
		msg == msg.upcase
	end

	def respond
		'Woah, chill out!'
	end
end

class Questions
	def match?(msg)
		msg.end_with? '?'
	end

	def respond
		'Sure.'
	end
end

class Anything
	def match?(msg)
		true
	end
	
	def respond
		'Whatever.'
	end
end

class Bob
	Parsers = [Dumb, Shouting, Questions, Anything]
	
	def hey(msg)
		Parsers.map(&:new).each do |parser|
			return parser.respond if parser.match? msg
		end
	end
end
