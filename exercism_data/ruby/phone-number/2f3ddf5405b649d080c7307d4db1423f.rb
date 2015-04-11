class PhoneNumber

  attr_accessor :raw_number

  def initialize(input)
    @raw_number = input
  end

  def number
    clean_number
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def line_number
    number[6..-1]
  end

  def clean_number
    if us_number?
      digits[1..-1]
    elsif invalid_number?
      invalid_number
    else
      digits
    end
  end

  def digits
    raw_number.scan(/\d+/).join
  end

  def invalid_number
    "0000000000"
  end

  def us_number?
    digits.length == 11 && digits.start_with?("1")
  end

  def invalid_number?
    (digits.length == 11 || digits.length == 9) ||
    (digits.length == 12 && digits.start_with?("1"))
  end

end
