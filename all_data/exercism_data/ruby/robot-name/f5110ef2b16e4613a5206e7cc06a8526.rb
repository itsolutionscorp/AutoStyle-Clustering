class Robot
	attr_reader :name
	MAX_NAMES = 52*52*1000 #the first two characters have 52 possible values, the last three have 1000
	@@usedNames = []

	def initialize
		reset
	end

	def reset
		if @@usedNames.size >= MAX_NAMES
			puts "no more names left"
		else
			#this will loop until it finds a name, which could take a really long time as time goes on.
			begin 
				@name = getRandomLetters(2) + getRandomDigits(3).to_s
			end while @@usedNames.include? @name
			@@usedNames << @name
		end
	end

	def getRandomLetters(count)
		count.times.map { [*('a'..'z'), *('A'..'Z')].sample }.join
	end
	def getRandomDigits(count)
		count.times.map { [*('0'..'9')].sample }.join
	end

end
