class Binary
	attr_reader :number

	def initialize(number)
		@number = number
	end

	def to_decimal
		is_binary?

    decimal = @b_numbers.each_with_index.reduce(0) do |sum, (value, index)|
      sum + value.to_i * (2 ** index)
    end
	end

	private

	def is_binary?
		if number.match(/[a-zA-Z2-9]/)
			@b_numbers = []
		else
			@b_numbers = number.chars.reverse { |d| d.to_i }
		end
	end
end
