class Robot
	@@nameList = []
	attr_accessor :name;

	CHAR_RANGE = ("A".."Z")
	NUMBER_RANGE = (0..9)

	STRING_LENGTH = 2
	NUMBER_LENGTH = 3

	TOTAL_COMBINATIONS = (CHAR_RANGE.count ** STRING_LENGTH) * (NUMBER_RANGE.count ** NUMBER_LENGTH)
	
	def initialize
		@name = generateName
	end

	def generateName
		raise "No more names available." if TOTAL_COMBINATIONS == @@nameList.count

		name = getRandomString(STRING_LENGTH, CHAR_RANGE)
		name = name + getRandomString(NUMBER_LENGTH, NUMBER_RANGE)

		name = generateName if @@nameList.include? name 

		@@nameList.push(name)
		name

	end

	def getRandomString(length, range)
		randomString = ""
		length.times { randomString << range.to_a.sample.to_s }
		randomString
	end

	def reset
		@name = generateName
	end

	private :generateName, :getRandomString



end
      
