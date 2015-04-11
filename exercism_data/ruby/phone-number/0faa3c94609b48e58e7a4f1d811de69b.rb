class PhoneNumber

  def initialize(raw_number)
    @raw_number = raw_number
  end

  def number
    @number ||= validated_digits
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

  def validated_digits
    valid? ? digits : "0000000000"
  end

  def valid?
    digits.length == 10
  end

  def digits
    @digits ||= scanned_digits_less_leading_one
  end

  def scanned_digits_less_leading_one
    len = scanned_digits.length
    if len == 11 && scanned_digits[0] == "1"
      scanned_digits.slice(1, len)
    else
      scanned_digits
    end
  end

  def scanned_digits
    @raw_number.scan(/\d+/).join
  end
end
