class Bob

	def hey
		response
	end

	def response(words)
		incoming = TeenagerSpeak.new(words)
		return "Fine. Be that way!" if incoming == silence?
		return	"Sure." if incoming == question?
		return	"Woah, chill out!" if incoming == shout?
		"Whatever." 
	end
end

class TeenagerSpeak

	def silence?(words)
		words.strip.empty?
	end

	def question?(words)
		words =~ /\?\z/ 
	end

	def shout?(words)
		words == words.upcase
	end

end
