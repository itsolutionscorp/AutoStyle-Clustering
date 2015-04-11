class Robot
	A_THRU_Z = ('A'..'Z').to_a
	attr_reader :name

	def initialize
		@@names ||= []
		reset
	end

	def reset
		@name = generate_name
		@@names << @name
	end

	protected

	def is_taken? name
		@@names.include? name
	end

	def generate_name
		name = generate_alphas + generate_digits
		name = generate_name if is_taken? name
		name
	end

	def generate_alphas
		generate_alpha + generate_alpha
	end

	def generate_digits
		generate_digit + generate_digit + generate_digit
	end

	def generate_alpha
		A_THRU_Z[rand(A_THRU_Z.count)]
	end

	def generate_digit
		rand(10).to_s
	end
end
