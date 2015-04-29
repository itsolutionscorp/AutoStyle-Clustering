class Robot
	
	def name 
		if @name == "" 
			charRange = ("A".."Z")
			numberRange = (0..9)

			@name = getRandomString(2, charRange)
			@name = @name + getRandomString(3, numberRange)
		end

		@name
	end
	
	def initialize
		@name = ""		
	end

	def getRandomString(length, range)
		randomString = ""
		length.times { randomString << range.to_a[rand(range.count)].to_s }
		randomString
	end

	def reset
		@name = ""
	end


end
