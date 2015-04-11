class PhoneNumber

  def initialize(raw_number)
    @raw_number = raw_number
  end

  def number
    @number ||= validate make_number @raw_number
  end

  def area_code
    @area_code ||= number.slice(0,3)
  end

  def prefix
    @prefix ||= number.slice(3,3)
  end

  def location
    @location ||= number.slice(6,4)
  end

  def to_s
    "(#{area_code}) #{prefix}-#{location}"
  end

  private
  def validate(digits)
    return "0000000000" unless valid?(digits)
    digits
  end

  def make_number(raw_number)
    trim_leading_one raw_number.scan(/\d+/).join
  end

  def valid?(digits)
    digits.length == 10
  end

  def trim_leading_one(digits)
    len = digits.length
    return digits.slice(1, len) if (len == 11 && digits[0] == "1")
    digits
  end
end
