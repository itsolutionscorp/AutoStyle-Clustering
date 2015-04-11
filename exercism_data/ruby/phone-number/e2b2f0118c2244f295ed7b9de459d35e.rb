class Phone
  def initialize(number)
    @number = number.to_s
  end

  def number
    number = digits_only @number
    return number if assumed_good_number? number
  
    number = handle_9_digits number
    number = handle_11_digits number
  end

  # The first block in the 3-3-4 phone number scheme.
  def area_code
    number[0..2]
  end

  # The second block in the 3-3-4 phone number scheme.
  def prefix
    number[3..5]
  end

  # The third block in the 3-3-4 phone number scheme.
  def line_number
    number[6..-1]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  def digits_only(number)
    number.gsub(/[^[:digit:]]/, '')
  end

  def assumed_good_number?(number)
    number.size == 10
  end

  def handle_9_digits(number)
    return number if number.size > 10
    stripped = strip_first_char(number)
    convert_to_zeros
  end

  def handle_11_digits(number)
    return number if number.size != 11
    valid_11_digit_number?(number) ? strip_first_char(number) : convert_to_zeros
  end

  def valid_11_digit_number?(number)
    number.start_with? "1"
  end

  def strip_first_char(number)
    number[1..-1]
  end

  def convert_to_zeros
    "0" * 10
  end
end
