class PhoneNumber
  attr_reader :number

  BAD_NUMBER = '0000000000'

  def initialize(number)
    @number = clean(number)
  end

  def clean(number)
    number = number.gsub(/[^a-zA-z0-9]/, '')
    return BAD_NUMBER if bad_number?(number)
    number.length == 10 ? number : number[1..number.length]
  end

  def bad_number?(number)
    number =~ /[a-zA-Z]/ || invalid_length?(number) || (number.length == 11 && number[0] != '1')
  end

  def invalid_length?(number)
    number.length < 10 || number.length > 11
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end
end

number = PhoneNumber.new("(123) 456-7890").number
