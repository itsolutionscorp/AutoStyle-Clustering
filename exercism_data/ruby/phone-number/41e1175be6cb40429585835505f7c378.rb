class Phone
  attr_reader :number, :area_code, :prefix, :line_number

  def initialize(input)
    phone_digits = digits_of input
    @number      = validate phone_digits
    @area_code   = @number[0..2]
    @prefix      = @number[3..5]
    @line_number = @number[6..9]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  def digits_of(input)
    input.gsub /\D/, ''
  end

  def has_us_country_code?(input)
    input.length == 11 ? input[0] == '1' : false
  end

  def validate(phone_digits)
    if phone_digits.length == 10
      phone_digits
    elsif has_us_country_code? phone_digits
      phone_digits[1..-1]
    else
      "0000000000"
    end
  end
end
