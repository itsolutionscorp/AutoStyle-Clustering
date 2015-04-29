class PhoneNumber
  attr_reader :number

  def initialize(number)
    @number = sanitize(number)
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..10]}"
  end

  private

  def sanitize(str)
    return invalid_return_value if str.match(/[a-zA-Z]/)
    just_numbers = strip_non_digits(str)
    national_number = strip_international_code(just_numbers)
    correct_length?(national_number) ? national_number : invalid_return_value
  end

  def strip_non_digits(str)
    str.gsub(/[\D]/, "")
  end

  def strip_international_code(str)
    valid_international_number?(str) ? str[1..-1] : str
  end

  def valid_international_number?(str)
    str.length == 11 && str[0] == "1"
  end

  def correct_length?(str)
    str.length == 10
  end

  def invalid_return_value
    "0"*10
  end
end
