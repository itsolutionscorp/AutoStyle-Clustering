class Robot
	
	def name 
		@name
	end
	
	def initialize
		@name = ""	
		@nameList = []	

		@charRange = ("A".."Z")
		@numberRange = (0..9)

		@name = generateName
	end

	def generateName
		name = getRandomString(2, @charRange)
		name = name + getRandomString(3, @numberRange)

		name = generateName if @nameList.include? name 

		@nameList.push(name)
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
