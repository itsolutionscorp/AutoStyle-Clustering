class Robot
	@@LETTER_RANGE = 'A'..'Z'
	@@DIGIT_RANGE = '0'..'9'
	@@exist_robots = {}
	@@robots_count = 0

	def initialize()
		raise 'can\'t have more robots!' if @@exist_robots.count == max_num_of_robot

		@@robots_count += 1
		@id = @@robots_count
		generate_robot
	end

	def name
		@@exist_robots[@id]
	end

	def reset
		old_name = self.name
		generate_robot
	end

	# doesn't need in this case
	def destroy
		# can't delete a robot exists no more
		if @@exist_robots[@id]
			@@exist_robots[@id] = nil
			@@robots_count -= 1
			@id = nil
		end
	end

	private

	def generate_robot
		loop do
			@name = generate_name
			break unless @@exist_robots.has_value?(@name)
		end
		@@exist_robots[@id] = @name
	end

	def generate_name
		rand_string(@@LETTER_RANGE, 2) + rand_string(@@DIGIT_RANGE, 3)
	end

	def rand_string(range, count = 1)
		Array.new(count) { range.to_a[rand(range.count)] }.join
	end

	def max_num_of_robot
		@@LETTER_RANGE.count ** 2 + @@DIGIT_RANGE.count ** 3
	end
end
