class PhoneNumber
  attr_reader :number

  def initialize(number)
    validator = PhoneNumberValidator.new "0000000000", '1'
    @number   = validator.process number
  end

  def area_code
    number[0...3]
  end

  def to_s
    "(#{area_code}) #{local_number}"
  end

  private

  def local_number
    "#{number[3...6]}-#{number[6..-1]}"
  end
end

class PhoneNumberValidator
  def initialize(default, country_code)
    @default      = default
    @country_code = country_code
    @digits       = ""
  end

  def process(number)
    @digits = digitalize number
    remove_country_code
    validate_length
    @digits
  end

  private

  def digitalize(number)
    number.gsub /\D/, ''
  end

  def remove_country_code
    @digits = @digits[1..-1] if has_country_code
  end

  def has_country_code
    @digits.length == 11 and @digits[0] == @country_code
  end

  def validate_length
    @digits = @digits.length == 10 ? @digits : @default
  end
end
