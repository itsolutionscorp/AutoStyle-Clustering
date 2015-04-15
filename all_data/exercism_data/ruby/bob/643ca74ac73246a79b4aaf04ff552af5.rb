class Bob

	def hey(input)
			case 
			when input.nil?
				Response.confrontational
			when Input.all_uppercase?(input)
				Response.chill
			when Input.question?(input)
				Response.agreement
			when Input.digits?(input)
				Response.chill
			when Input.no_frills?(input) 
				Response.indifferent
			else
				Response.confrontational
			end
	end
end

class Input

	def self.all_uppercase?(input)
		input.match( /[A-Z]{2,}/ )
	end

	def self.question?(input)
		input.match(/[?]$/)
	end

	def self.digits?(input)
		input.match(/\d/)
	end

	def self.no_frills?(input)
		input.match(/.+/)
	end

end

class Response

	def self.indifferent
		'Whatever.'
	end

	def self.confrontational
		'Fine. Be that way.'
	end

	def	self.chill
	 	'Woah, chill out!'
	end

	def self.agreement
		'Sure.' 
	end

end
