

class Robot
	def initialize
		@myName = generateName
	end
	
	def name
		@myName
	end
	
	def reset
		@myName = generateName
	end
	
	def generateName
		randomString(2) + rand(100...999).to_s
	end
	
	def randomString(numLetters)
		return ('a'..'z').to_a.shuffle[0,numLetters].join
	end

end
