class PhoneNumber
  attr_reader :number

  def initialize(raw_number)
    @number = validate make_number raw_number
  end

  def area_code
    @area_code ||= @number.slice(0,3)
  end

  def prefix
    @prefix ||= @number.slice(3,3)
  end

  def location
    @location ||= @number.slice(6,4)
  end

  def to_s
    @pretty_print ||= "(#{area_code}) #{prefix}-#{location}"
  end

  private

  def validate(digits)
    return "0000000000" if invalid(digits)
    trim_leading_one digits
  end

  def make_number(raw_number)
    raw_number.gsub(/\D+/) { "" }
  end

  def invalid(digits)
    len = digits.length
    len < 10 || len > 11 || len == 11 && digits[0] != "1"
  end

  def trim_leading_one(digits)
    len = digits.length
    return digits.slice(1, len) if len == 11
    digits
  end

end
