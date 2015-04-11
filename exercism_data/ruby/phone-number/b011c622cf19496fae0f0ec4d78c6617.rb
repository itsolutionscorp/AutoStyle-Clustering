class Phone
  US_PHONE_NUMBER = /\A1?(?<area_code>\d{3})(?<prefix>\d{3})(?<line_number>\d{4})\Z/
  INVALID = "0" * 10

  attr_reader :number

  def initialize(digits)
    @parts = sanitize(digits).match(US_PHONE_NUMBER)
    @number = @parts ? @parts.captures.join : INVALID
  end

  def area_code
    @parts['area_code']
  end

  def prefix
    @parts['prefix']
  end

  def line_number
    @parts['line_number']
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  def sanitize(digits)
    digits.gsub(/\D/, '')
  end
end
