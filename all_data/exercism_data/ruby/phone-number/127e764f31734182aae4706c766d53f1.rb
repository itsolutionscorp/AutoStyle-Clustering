class PhoneNumber

  attr_reader :number

  def initialize(input_number)
    @number = produce_valid_number(input_number)
  end

  def produce_valid_number(raw_number)
    digits = get_digits(raw_number)
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

  def middle_three_digits
    number[3..5]
  end

  def last_four_digits
    number[-4..-1]
  end

  def to_s
    "(" + area_code + ") " + middle_three_digits + "-" + last_four_digits
  end

end
