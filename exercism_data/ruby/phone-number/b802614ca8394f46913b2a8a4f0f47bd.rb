class PhoneNumber
  INVALID_NUMBER_OUTPUT = "0000000000"
  US_COUNTRY_CODE = "1"

  attr_reader :phone_number_input

  def initialize(phone_number_input)
    @phone_number_input = sanitize(phone_number_input)
  end

  def number
    if invalid_number?
      INVALID_NUMBER_OUTPUT
    else
      number_without_country_code
    end
  end

  def area_code
    number_without_country_code[0..2]
  end

  def to_s
    n = number_without_country_code
    "(#{n[0..2]}) #{n[3..5]}-#{n[6..9]}"
  end

  private

  def sanitize(input)
    input.gsub(/\D/,"")
  end

  def invalid_number?
    phone_number_input.length < 10 ||
    phone_number_input.length > 11 ||
      (phone_number_input.length == 11 && phone_number_input[0] != US_COUNTRY_CODE)
  end

  def number_without_country_code
    if phone_number_input.length == 11
      phone_number_input[1..-1]
    else
      phone_number_input
    end
  end
end
