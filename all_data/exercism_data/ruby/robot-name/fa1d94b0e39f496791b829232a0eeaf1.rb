class Robot
	attr_reader :name
	@@names = ['']

	def initialize
		reset
	end

	def reset
		@name = randLetter + randLetter + randNumbers while @@names.include?(@name)
		@@names << @name
	end

	:private

	def randLetter
		(Random.rand(26) + "A".ord).chr
	end

	def randNumbers
		sprintf("%03d", Random.rand(1000))
	end
end
