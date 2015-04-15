class PhoneNumber
  attr_reader :number

  def initialize(number)
    digits  = digitalize number
    @number = validate digits
  end

  def area_code
    number[0...3]
  end

  def to_s
    "(#{area_code}) #{local_number}"
  end

  private

  def digitalize(number)
    number.gsub /\D/, ''
  end

  def validate(digits)
    digits = digits[1..-1] if digits.length == 11 and digits[0] == '1'
    digits.length == 10 ? digits : "0000000000"
  end

  def local_number
    "#{number[3...6]}-#{number[6..-1]}"
  end
end
