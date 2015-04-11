class Robot
	attr_accessor :name


	def initialize
		self.reset
	end


	def reset
		r = Random.new

		while 1
			name = [
				(0..1).map { r.rand(65..90).chr },
				(0..2).map { r.rand(9).to_s }
			].flatten.join

			# Only assign a name if it's globally unique
			if self.unique? name
				@name = name
				break
			end
		end
	end


	# Test if the +name+ is globally unique
	def unique? name
		robots = ObjectSpace.each_object(Robot).to_a
		raise "There are 17,676 robots, we've run out of names" if robots.length > 17676
		robots.each { |r| return false if r.name == name }
		return true
	end
end
