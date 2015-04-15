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
    return parse INVALID_NUMBER  if length > 11 or length < 10
    return parse INVALID_NUMBER  if string.match(/\D/)

    if length == 11
      return parse(string.start_with?("1") ? string[1..-1] : INVALID_NUMBER)
    end

    return parse string
  end

  private

  def parse(digits)
    [digits[0,3], digits[3,3], digits[6,4]]
  end
end
