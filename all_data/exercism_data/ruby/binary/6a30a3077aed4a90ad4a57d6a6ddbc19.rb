class Binary
	attr_reader :binary

	def initialize(binary)
    @binary = binary
	end

  def to_decimal
		if binary.match(/[a-z2-9]/)
			@decimal = 0
		else
			@b_numbers = @binary.chars.reverse.map do |b|
				b.to_i
			end

			@decimals = @b_numbers.each_with_index.map do |d, i|
	    	2 ** i * d
	    end

	    @decimal = @decimals.reduce(0) do
	      |sum, d| sum + d
	    end
		end

		@decimal
  end
end
