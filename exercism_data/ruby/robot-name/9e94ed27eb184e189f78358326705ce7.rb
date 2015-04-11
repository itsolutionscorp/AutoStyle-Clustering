class Robot

	@@used_names = []

	attr_reader :name

	def initialize
		reset
	end

	def reset
		@name = check_and_generate_random_name
	end

	private

	def check_and_generate_random_name
		candidate = generate_random_name
		while @@used_names.include? candidate
			candidate = generate_random_name
		end
		@@used_names << candidate
		candidate
	end

	def generate_random_name
		random_letter + random_letter + random_digits
	end

	def random_letter
		[*'A'..'Z'].shuffle.first
	end

	def random_digits
		rand(999).to_s.rjust(3, '0')
	end
end
