class PhoneNumber
  attr_reader :number

  BAD = '0000000000'

  def initialize(number)
    @number = test_validity(number)
  end

  def test_validity(number)
    temp = clean(number)
    if test_for_letters(temp)
      BAD
    else
      test_length(temp)
    end
  end

  def clean(number)
    number.gsub(/[^a-zA-Z0-9]/,'')
  end

  def test_for_letters(number)
    number =~ /[a-zA-Z]/
  end

  def test_length(number)
    if number.length < 10 || number.length > 11 || number.length == 11 && number[0] != '1'
      BAD
    elsif number.length == 11 && number[0] == '1'
      number[1..number.length]
    else
      number
    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end
end
