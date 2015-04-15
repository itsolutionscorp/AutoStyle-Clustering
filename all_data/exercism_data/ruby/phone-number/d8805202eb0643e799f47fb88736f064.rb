class PhoneNumber
  attr_reader :number

  def initialize phone_number
    @number = phone_number
    normalize
  end

  def area_code
    number.slice(0,3)
  end

  def phone_number_first_three
    number.slice(3,3)
  end

  def phone_number_last_four
    number.slice(6,4)
  end

  def to_s
    "(#{area_code}) #{phone_number_first_three}-#{phone_number_last_four}"
  end

  private
  def normalize
    strip_unwanted_characters
    if number_valid?
      if number.length == 11
        strip_country_digit
      end
    else
      @number = "0000000000"
    end
  end

  def strip_unwanted_characters
    @number = number.delete('^0-9')
  end

  def number_valid?
    number.length == 10 || (number.length == 11 && number.slice(0,1) == "1")
  end

  def strip_country_digit
    @number = number.slice(1,10)
  end
end
