class Robot
	@@names = []

	def initialize
		@name = generate_name
		@@names.push(@name)
	end

	def name
		@name
	end

	def reset
		@name = generate_name
	end

	def all_names
		puts @@names.to_s
	end

	private

	def generate_name
		letters = (1..2).map do |letter|
			('A'..'Z').to_a[rand(26)]
		end

		numbers = (1..3).map do |number|
			rand(0..9)
		end

		name = letters.concat(numbers).join

		if @@names.index(name)
			generate_name
		else
			name
		end
	end
end
