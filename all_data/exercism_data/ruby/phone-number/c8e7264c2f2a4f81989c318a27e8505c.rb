class PhoneNumber
  attr_reader :number

  def initialize phone_number
    @number = phone_number
    normalize
  end

  def area_code
    number.slice(0,3)
  end

  def first_three
    number.slice(3,3)
  end

  def last_four
    number.slice(6,4)
  end

  def set_number_without_country_digit
    if number.length == 11
      @number = number.slice(1,10)
    end
  end

  def to_s
    "(#{area_code}) #{first_three}-#{last_four}"
  end

  private
  def normalize
    strip_unwanted_characters
    strip_country_digit
  end

  def strip_unwanted_characters
    @number = number.delete('^0-9')
  end

  def strip_country_digit
    if number_valid?
      set_number_without_country_digit
    else
      set_default_number
    end
  end

  def number_valid?
    number.length == 10 || (number.length == 11 && number.slice(0,1) == "1")
  end

  def set_default_number
    @number = "0000000000"
  end
end
