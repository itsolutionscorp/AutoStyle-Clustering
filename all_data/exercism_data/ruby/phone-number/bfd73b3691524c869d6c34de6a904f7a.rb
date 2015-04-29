class PhoneNumber
  attr_reader :prefix, :line_number, :area_code

  def initialize(digits)
    @area_code, @prefix, @line_number = PhoneNumberCleaner.new(digits).clean
  end

  def number
    area_code + prefix + line_number
  end

  def to_s
    sprintf("(%s) %s-%s", area_code, prefix, line_number)
  end
end

class PhoneNumberCleaner
  attr_reader :string, :length

  INVALID_NUMBER = "0000000000"

  def initialize(raw_string)
    self.string = raw_string
    @length     = string.length
  end

  def string=(raw_string)
    @string = raw_string.gsub(/\W/, '')
  end

  def clean
    digits = string

    digits = INVALID_NUMBER      if length > 11 or length < 10
    digits = INVALID_NUMBER      if length == 11 and digits[0] != "1"
    digits = INVALID_NUMBER      if digits.match(/\D/)

    digits = digits.slice(1..-1) if length == 11 and digits[0] == "1"

    return [digits[0,3], digits[3,3], digits[6,4]]
  end
end
