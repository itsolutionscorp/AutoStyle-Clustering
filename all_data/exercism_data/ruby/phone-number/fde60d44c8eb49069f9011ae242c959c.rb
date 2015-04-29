class PhoneNumber
  def initialize(raw)
    @raw = raw
  end

  def number
    @clean ||= clean
  end

  def to_s
    "(%d) %d-%g" % [area_code, number[3..5], number[6..9]]
  end

  def area_code
    number[0, 3]
  end

private
  def clean
    return digits if digits.length == 10
    return localize if digits.length == 11 && localize
    return '0000000000'
  end

  def digits
    @digits ||= @raw.scan(/\d+/).join
  end

  def localize
    @localized ||= digits[1..10] if digits[0] == '1'
  end
end
