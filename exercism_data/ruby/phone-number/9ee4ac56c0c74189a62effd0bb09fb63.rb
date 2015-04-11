class PhoneNumber

  attr_reader :number

  def initialize(input_number)
    @number = clean_number(input_number)
  end

  def area_code
    number[0..2]
  end

  def clean_number(dirty_number)
    cleaned_number = dirty_number.scan(/\w/).join
    if eleven_digits_and_valid?(cleaned_number)
      return cleaned_number[1..-1]
    elsif cleaned_number.length != 10
      return "0"*10
    else
      return cleaned_number
    end
  end

  def eleven_digits_and_valid?(cleaned_number)
    cleaned_number.length == 11 && first_digit_one?(cleaned_number)
  end

  def first_digit_one?(number)
    number.split("").first == "1"
  end

  def to_s
    "(" + area_code + ") " + number[3..5] + "-" + number[6..9]
  end

end
