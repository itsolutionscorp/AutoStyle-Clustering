# As for Response, consider ways of making it "smarter". Maybe if you pass an Input to Response, Response could know the appropriate method to respond with, and that could help reduce some complexity in the decision tree in Bob#hey, and help get rid of the class method smell.

class Bob
	def hey(input)
		response = Response.new(input)
		response.anwser
	end
end

class Response

	def initialize(input)
		@input 	= input
		@matcher = Matcher.new(input)
	end

	def anwser
		case 
		when @input.nil?
			confrontational
		when @matcher.all_uppercase?
			chill
		when @matcher.question?
			agreement
		when @matcher.digits?
			chill
		when @matcher.no_frills? 
			indifferent
		else
			confrontational
		end
	end

private
	def indifferent
		'Whatever.'
	end

	def confrontational
		'Fine. Be that way.'
	end

	def	chill
	 	'Woah, chill out!'
	end

	def agreement
		'Sure.' 
	end

end

class Matcher

	def initialize(input)
		@input = input
	end

	def all_uppercase?
		@input.match( /[A-Z]{2,}/ )
	end

	def question?
		@input.match(/[?]$/)
	end

	def digits?
		@input.match(/\d/)
	end

	def no_frills?  
		@input.match(/.+/)
	end

end
