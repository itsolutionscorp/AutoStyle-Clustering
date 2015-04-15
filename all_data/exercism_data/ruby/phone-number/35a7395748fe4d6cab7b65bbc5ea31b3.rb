class PhoneNumber

  def initialize(number)
    @number = number
  end

  def area_code
    number[0..2]
  end

  def number
    digits = @number.tr("^0-9","")
    if us_number_with_country_code(digits)
      digits[1..10]
    elsif invalid_number(digits)
      "0000000000"
    else 
      digits
    end
  end

  def to_s
    new_number = number.gsub(number[0], "(" + number[0])
    new_number = new_number.gsub(new_number[3], new_number[3] + ") ")
    new_number = new_number.gsub(new_number[8], new_number[8] + "-")
  end

  def us_number_with_country_code(digits)
    digits.length == 11 && digits[0] == "1"
  end

  def invalid_number(digits)
    digits.length != 10
  end
end
