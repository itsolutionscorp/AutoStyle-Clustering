class Bob
	def hey(input)

		if Request.nothing?(input)
			answer = 'Fine. Be that way!'
		elsif Request.yell?(input)
			answer = 'Woah, chill out!'
		elsif Request.question?(input)
			answer = 'Sure.'
		else
			answer = 'Whatever.'
		end

		answer
	end
end

class Request
	def self.nothing?(request)
		return request.strip.empty?
	end

	def self.yell?(request)
		return request.match(/[a-zA-z]/) && request.upcase == request
	end

	def self.question?(request)
		return request.end_with?('?')
	end
end
