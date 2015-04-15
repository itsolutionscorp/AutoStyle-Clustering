class Bob
	def hey(greeting)
		greeting = greeting.to_s.strip
		return Hormones.respond(greeting)		
	end
end

class Hormones

	def self.respond(greeting)
		if greeting.empty?
			return 'Fine. Be that way!'
		elsif greeting.upcase == greeting
			return 'Woah, chill out!'
		elsif greeting.chars.last == '?'
			return 'Sure.'
		else
			return 'Whatever.'
		end		
	end
end
