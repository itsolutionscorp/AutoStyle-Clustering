class Robot
	@@NAME_LEFT_RANGE = 'A'..'Z'
	@@NAME_RIGHT_RANGE = '0'..'9'
	@@exist_names = []

	def initialize()
		generate_robot 
	end

	def name
		@name
	end

	def reset
		old_name = @name
		generate_robot
		@@exist_names.delete(old_name)
	end

	private

	def generate_robot
		raise 'can\'t have more robots!' if @@exist_names.count == max_num_of_robot
		begin
			@name = generate_name
		end while @@exist_names.include?(@name)
		@@exist_names << @name
	end

	def generate_name
		rand_string(@@NAME_LEFT_RANGE, 2) + rand_string(@@NAME_RIGHT_RANGE, 3)
	end

	def rand_string(range, count = 1)
		result = []
		count.times { result << range.to_a[rand(range.count)] }
		result.join
	end

	def max_num_of_robot
		@@NAME_LEFT_RANGE.count ** 2 + @@NAME_RIGHT_RANGE.count ** 3
	end

	# doesn't need in this case
	def destroy
	end
end
