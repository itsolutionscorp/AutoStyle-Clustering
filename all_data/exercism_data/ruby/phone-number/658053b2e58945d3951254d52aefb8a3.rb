class PhoneNumber

  attr_accessor :raw_number

  def initialize(input)
    @raw_number = input
  end

  def number
    if valid_number?
      clean_number
    else
      default_number
    end
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

  private

  def clean_number
    if us_number?
      digits[1..-1]
    else
      digits
    end
  end

  def digits
    raw_number.scan(/\d+/).join
  end

  def default_number
    "0000000000"
  end

  def us_number?
    digits.length == 11 && digits.start_with?("1")
  end

  def valid_number?
    !too_long? && !too_short?
  end

  def too_long?
    (digits.length == 11 && !digits.start_with?("1")) || digits.length > 11
  end

  def too_short?
    digits.length <= 9
  end

end
