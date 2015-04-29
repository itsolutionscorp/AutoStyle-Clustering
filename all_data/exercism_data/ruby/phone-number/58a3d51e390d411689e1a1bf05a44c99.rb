class PhoneNumber
  attr_accessor :number

  def initialize(number)
    @number = number.scan(/\w/).join
    validate_number
  end

  def validate_number
    self.number = number[1..-1] if valid_with_leading_one?
    self.number = '0' * 10 if invalid_number?
  end

  def valid_with_leading_one?
    number.length == 11 && number[0] == '1'
  end

  def invalid_number?
    number.length != 10 || number.scan(/\d/).length != 10
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  def area_code
    number[0..2]
  end

end
