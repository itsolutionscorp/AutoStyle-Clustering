class Phone
  # http://en.wikipedia.org/wiki/Telephone_numbers_in_the_United_States

  COUNTRY_CODE = "1"

  AREA_CODE_LENGTH = 3
  CENTRAL_OFFICE_CODE_LENGTH = 3
  SUBSCRIBER_NUMBER_LENGTH = 4

  COUNTRY_CODE_LENGTH = COUNTRY_CODE.length
  DOMESTIC_LENGTH = AREA_CODE_LENGTH + CENTRAL_OFFICE_CODE_LENGTH + SUBSCRIBER_NUMBER_LENGTH
  COUNTRY_CODED_LENGTH = DOMESTIC_LENGTH + COUNTRY_CODE_LENGTH

  BAD_NUMBER = "0" * DOMESTIC_LENGTH

  def initialize(number)
    @number = number
  end

  def number
    if normalized_number.length == DOMESTIC_LENGTH
      normalized_number
    else
      BAD_NUMBER
    end
  end

  def area_code
    number[0, AREA_CODE_LENGTH]
  end

  def to_s
    pretty_number
  end

  private

  def pretty_number
    "(#{area_code}) #{central_office_code}-#{subscriber_number}"
  end

  def central_office_code
    number[AREA_CODE_LENGTH, CENTRAL_OFFICE_CODE_LENGTH]
  end

  def subscriber_number
    number[-SUBSCRIBER_NUMBER_LENGTH..-1]
  end

  def normalized_number
    if country_coded?
      digits[COUNTRY_CODE_LENGTH..-1]
    else
      digits
    end
  end

  def country_coded?
    digits.length == COUNTRY_CODED_LENGTH && digits[0] == COUNTRY_CODE
  end

  def digits
    @number.gsub(/\D/, "")
  end
end
