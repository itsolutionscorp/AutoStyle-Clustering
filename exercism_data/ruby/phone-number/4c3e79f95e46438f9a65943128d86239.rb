class PhoneNumber
  attr_reader :number

  BAD_NUMBER = '0000000000'

  def initialize(number)
    @number = clean(number)
  end

  def clean(number)
    test_number = number.gsub(/[^a-zA-z0-9]/, '')
    return BAD_NUMBER if test_number =~ /[a-zA-Z]/ || invalid_length?(test_number)
    if test_number.length == 11
      if test_number[0] == '1'
        test_number[1..test_number.length]
      else
        BAD_NUMBER
      end
    else
      test_number
    end
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

number = PhoneNumber.new("19876543210").number
