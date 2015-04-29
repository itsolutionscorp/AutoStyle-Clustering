class Robot
	@@used_names = []

	def initialize
		@name = generatename
	end

	def reset
		old_name = @name
		@name = generatename
		@@used_names.delete(old_name)
	end

	def generatename
		new_name = randcharacter + randcharacter + Random.rand(0..9).to_s + Random.rand(0..9).to_s + Random.rand(0..9).to_s
		while @@used_names.include? new_name
			new_name = generatename
		end
		@@used_names << new_name
		return new_name
	end

	def randcharacter
		if Random.rand(2) == 1
			return Random.rand(65..90).chr
		else
			return Random.rand(97..122).chr
		end
	end

	attr_accessor :name
end
