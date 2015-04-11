# phone_number.rb
class PhoneNumber
  BAD_NUMBER = '0' * 10

  attr_accessor :number

  def initialize(number)
    @number = parse(number)
  end

  def parse(number)
    return BAD_NUMBER if number =~ /[^\d\(\)\.\s\-]/
    digits = number.scan(/\d/)
    digits.shift if digits.first == '1' && digits.size == 11
    return BAD_NUMBER if digits.size != 10
    digits.join
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..-1]}"
  end
end
