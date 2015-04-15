class Responder
	attr_accessor :speach

	def initialize(s)
		self.speach = s
	end

	def fine
		"Fine. Be that way!" if speach.nil? or speach.length == 0
	end

	def chill
		"Woah, chill out!" if speach == speach.upcase
	end

	def whatever
		"Whatever." if speach[-1] == "." or speach[-1] == "!"
	end

	def sure
		"Sure." if speach[-1] == "?"
	end

	def to_s
		fine || chill ||whatever || sure
	end
end

class Bob

	def hey (speach)
		r = Responder.new(speach)
		r.to_s
#		if speach.nil? 			then return "Fine. Be that way!" end
#		if speach.length == 0 		then return "Fine. Be that way!" end
#		if speach == speach.upcase 	then return "Woah, chill out!" end
#		if speach[-1] == "."		then return "Whatever." end
#		if speach[-1] == "!"		then return "Whatever." end
#		if speach[-1] == "?" 		then return "Sure." end
	end

end
