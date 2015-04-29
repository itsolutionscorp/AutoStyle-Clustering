class PhoneNumber
  USA_PHONE_NUMBER_LENGTH = 10

  def initialize(number)
    @number = parse(number)
  end

  def number
    @number
  end

  def to_s
    "(%d) %d-%d" % [area_code, central_office_code, station_code]
  end

  def area_code
    @number[0..2]
  end

  def central_office_code
    @number[3..5]
  end

  def station_code
    @number[6..9]
  end

  def parse(input_number)
    number = strip_usa_country_code(only_digits(input_number))

    if valid?(number)
      number
    else
      default_number
    end
  end

  def only_digits(number)
    number.gsub(/[^\d]/, '')
  end

  def strip_usa_country_code(number)
    return number unless number.size == USA_PHONE_NUMBER_LENGTH + 1
    return number unless number.start_with?('1')
    number[1..-1]
  end

  def valid?(number)
    number.size == USA_PHONE_NUMBER_LENGTH
  end

  def default_number
    '0' * USA_PHONE_NUMBER_LENGTH
  end
end
