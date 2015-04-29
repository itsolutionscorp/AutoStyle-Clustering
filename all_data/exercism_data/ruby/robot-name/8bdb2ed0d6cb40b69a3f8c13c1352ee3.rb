class Robot
	def initialize
		@name = nil
	end
	
	def name
		@name ||= generate_name
	end

	def reset
		old_name = @name
		@name = generate_name
		@@names.delete old_name
	end

	private
	def generate_name
		@@names ||= []
		characters = ('A'..'Z').to_a
		numbers = (0..9).to_a
		new_name = ''
		loop do
			new_name = [
				characters.sample,
				characters.sample,
				numbers.sample,
				numbers.sample,
				numbers.sample,
			].join
			break unless @@names.include? new_name
		end
		@@names << new_name
		new_name
	end
end
