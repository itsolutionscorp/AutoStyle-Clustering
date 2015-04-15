class PhoneNumber

  def initialize(number)
    @number = number
  end

  def area_code
    number[0..2]
  end

  def middle_three
    number[3..5]
  end

  def last_four
    number[6..9]
  end

  def number
    digits = @number.delete("^0-9")
    if us_number_with_country_code?(digits)
      digits[1..10]
    elsif invalid_number?(digits)
      "0000000000"
    else 
      digits
    end
  end

  def to_s
    "(#{area_code}) #{middle_three}-#{last_four}"
  end

  def us_number_with_country_code?(digits)
    digits.length == 11 && digits[0] == "1"
  end

  def invalid_number?(digits)
    digits.length != 10
  end
end
