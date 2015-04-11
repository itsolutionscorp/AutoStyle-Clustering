class Bob
	def hey(greeting)
		return Hormones.new(greeting).respond
	end
end

class Hormones
	attr_accessor :greeting

	def initialize(greeting)
		@greeting = greeting.to_s.strip
	end

	def respond
		if upset
			return 'Fine. Be that way!'
		elsif bossy
			return 'Woah, chill out!'
		elsif apathetic
			return 'Sure.'
		else
			return 'Whatever.'
		end		
	end

	def apathetic
		return greeting.chars.last == '?'
	end

	def upset
		return greeting.empty?
	end

	def bossy
		return greeting.upcase == greeting
	end
end
