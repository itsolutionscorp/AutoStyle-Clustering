class Bob
	def hey(greeting)
		Hormones.new(greeting).respond
	end
end

class Hormones

	def initialize(greeting)
		@greeting = greeting.to_s.strip
	end

	def respond
		if upset
			'Fine. Be that way!'
		elsif bossy
			'Woah, chill out!'
		elsif apathetic
			'Sure.'
		else
			'Whatever.'
		end		
	end

private
	def apathetic
		@greeting.end_with?('?')
	end

	def upset
		@greeting.empty?
	end

	def bossy
		@greeting.upcase == @greeting
	end
end
