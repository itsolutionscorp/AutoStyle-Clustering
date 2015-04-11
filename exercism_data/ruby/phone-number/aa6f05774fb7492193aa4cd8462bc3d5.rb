class PhoneNumber
  attr_reader :digits

  INVALID_NUMBER = Array.new(10, 0)
  TEMPLATE = "(%d%d%d) %d%d%d-%d%d%d%d"

  def initialize(phone_number)
    @digits = clean(phone_number)
  end

  def number
    digits.join
  end

  def area_code
    digits.take(3).join
  end

  def to_s
    format(TEMPLATE, *digits)
  end

  private

  def clean(phone_number)
    return INVALID_NUMBER unless valid?(phone_number)

    digits = phone_number.scan(/\d/).map(&:to_i)
    digits.shift if digits.length == 11 && digits.first == 1
    digits.length == 10 ? digits : INVALID_NUMBER
  end

  def valid?(phone_number)
    phone_number =~ /^[0-9.() -]+$/
  end
end
