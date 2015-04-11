class PhoneNumber
  attr_accessor :number

  def initialize(number)
    @number = number.scan(/\d/).join
    validate_number
  end

  def validate_number
    self.number = number[1..-1] if valid_with_leading_one?
    self.number = '0' * 10 if invalid_number?
  end

  def valid_with_leading_one?
    self.number.length == 11 && self.number[0] == '1'
  end

  def invalid_number?
    self.number.length != 10
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..9]}"
  end

  def area_code
    number[0..2]
  end

end
