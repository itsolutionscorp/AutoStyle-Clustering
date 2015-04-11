class Response
	attr_accessor :reply

	def initialize(greeting_matcher, reply)
		@greeting_matcher = greeting_matcher
		@reply = reply
	end

	def responds_to_greeting?(greeting)
		@greeting_matcher.call(greeting)
	end
end

class Bob
	def initialize
		@responses = [ Response.new(lambda { |greeting| greeting.strip.empty? }, "Fine. Be that way!"),
		   			   Response.new(lambda { |greeting| greeting == greeting.upcase }, "Woah, chill out!"),
		   			   Response.new(lambda { |greeting| greeting.end_with? "?" }, "Sure."),
		   			   Response.new(lambda { |greeting| true }, "Whatever." ) ]
	end

	def hey(greeting)
		response = @responses.find do |response|
			response.responds_to_greeting? greeting
		end

		response.reply unless !response
	end
end
