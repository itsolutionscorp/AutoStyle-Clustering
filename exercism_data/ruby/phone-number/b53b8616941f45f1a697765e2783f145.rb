class Phone
	def initialize(digits)
		@digits = digits
		format_digits
	end

	def number
		@digits
	end

	def area_code
		@digits[0..2]
	end

	def to_s
		@digits.insert(0, '(').insert(4, ') ').insert(9, '-')
	end

	private

	def format_digits
		extract_numbers
		@digits = "0000000000" if is_bad_number?
		trim_usa_code
	end

	def extract_numbers
		@digits.delete!('^0-9')
	end

	def is_bad_number?
		if @digits.size == 10
			false
		elsif @digits.size == 11 and @digits[0] == "1"
			false
		else
			true
		end
	end

	def has_usa_code?
		true if @digits.size == 11 and @digits[0] == "1"
	end

  def trim_usa_code
  	@digits[0] = "" if has_usa_code?
  end

end
