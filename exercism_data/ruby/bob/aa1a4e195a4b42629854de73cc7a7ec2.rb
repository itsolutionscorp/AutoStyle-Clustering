class Bob
	def initialize
	end
	def hey(remark)
		replies = ["Sure.","Whoa, chill out!","Fine. Be that way!","Whatever."]
		remark.strip!
		numbers = remark.scan(/[a-zA-Z]+/).empty?
		if remark.empty?
			replies[2]
		elsif  remark.upcase == remark and !numbers
			replies[1]		
		elsif remark[-1] == "?"
			replies[0]
		else
			replies[3]
		end			
	end
end
