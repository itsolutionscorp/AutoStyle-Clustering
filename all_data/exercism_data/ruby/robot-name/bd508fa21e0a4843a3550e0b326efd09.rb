class Robot
	@@nameList = []
	@name = ""

	CHAR_RANGE = ("A".."Z")
	NUMBER_RANGE = (0..9)
	
	def name 
		@name
	end
	
	def initialize
		@name = generateName
	end

	def generateName
		name = getRandomString(2, CHAR_RANGE)
		name = name + getRandomString(3, NUMBER_RANGE)

		name = generateName if @@nameList.include? name 

		@@nameList.push(name)
		name

	end

	def getRandomString(length, range)
		randomString = ""
		length.times { randomString << range.to_a[rand(range.count)].to_s }
		randomString
	end

	def reset
		@name = generateName
	end


end
