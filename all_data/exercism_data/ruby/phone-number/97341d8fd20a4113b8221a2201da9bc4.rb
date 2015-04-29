class PhoneNumber
  def initialize(number)
    @number = parse(number)
  end

  def number
    @number
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(%d%d%d) %d%d%d-%d%d%d%d" % number.chars
  end

  def parse(input_number)
    number = input_number
    number = only_digits(number)
    number = strip_usa_country_code(number)
    number = invalid_number if invalid(number)
    number
  end

  def only_digits(number)
    number.gsub(/[^\d]/, '')
  end

  def strip_usa_country_code(number)
    return number[1..-1] if number.start_with?('1') and number.size == 11
    number
  end

  def invalid(number)
    number.size != 10
  end

  def invalid_number
    '0000000000'
  end
end
