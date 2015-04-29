module PhoneNumberValidator
  def valid_number?(validate)
    valid_length?(validate) || valid_country_code?(validate)
  end

  def valid_length?(validate)
    validate.length < 10 || validate.length > 11
  end

  def valid_country_code?(validate)
    validate[-11] != nil && validate[0] != "1"
  end
end

class PhoneNumber
  include PhoneNumberValidator

  attr_reader :number, :area_code

  def initialize(number)
    validate = number.gsub(/[^\d]/, '')

    valid_number?(validate) ? @number = "0" * 10 : @number = validate[-10..-1]
    @area_code = @number[0..2]
  end
  
  def to_s
    "(#{@area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end
end
