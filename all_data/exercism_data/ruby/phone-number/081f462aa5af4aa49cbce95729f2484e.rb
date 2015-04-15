class PhoneNumber

  attr_reader :number

  def initialize(raw_number)
    @number = parse_number(raw_number)
  end

  def area_code
    number[0,3]
  end

  def prefix
    number[3,3]
  end

  def suffix
    number[6,4]
  end

  def to_s
    "(%s) %s-%s" % [area_code, prefix, suffix]
  end

  private

  def parse_number(raw)
    digits = extract_digits(raw)
    trim(digits)
  end

  def extract_digits(string)
    return ZEROES if contains_letters?(string)
    string.scan(/\d+/).join
  end

  def trim(number)
    if valid_eleven_digit_number?(number)
      number[1, 10]
    else
      return ZEROES if out_of_range?(number)
      number
    end
  end

  def out_of_range?(number)
    number.to_s.length > 10 || number.to_s.length < 10
  end

  def contains_letters?(string)
    string.match(/[a-z]+/)
  end

  def valid_eleven_digit_number?(number)
    number.start_with?("1") && number.length == 11
  end

  ZEROES = "0000000000"

end
