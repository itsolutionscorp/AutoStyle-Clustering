class PhoneNumber

  attr_reader :input_number

  def initialize(input_number)
    @input_number = input_number
  end

  def number
    digits = get_digits(input_number)
    validate_number(digits)
  end

  def validate_number(digits)
    if us_number?(digits)
      return digits[1..-1]
    elsif digits.length != 10
      return invalid_number
    else
      return digits
    end
  end

  def get_digits(number)
    number.scan(/\w/).join
  end

  def invalid_number
    default_number
  end

  def default_number
    "0"*10
  end

  def us_number?(digits)
    digits.length == 11 && first_digit_one?(digits)
  end

  def first_digit_one?(number)
    number.split("").first == "1"
  end

  def area_code
    number[0..2]
  end

  def central_office_code
    number[3..5]
  end

  def subscriber_number
    number[-4..-1]
  end

  def to_s
    "(" + area_code + ") " + central_office_code + "-" + subscriber_number
  end

end
