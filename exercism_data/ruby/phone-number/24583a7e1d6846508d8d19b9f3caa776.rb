class PhoneNumber
  attr_accessor :phone_number

  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    return clean_number[1..-1] if us_number?
    return "0" * 10 if invalid_number?
    return clean_number if valid_number?
  end

  def clean_number
    phone_number.gsub(/\D/, "")
  end

  def us_number?
    clean_number.length == 11 && clean_number[0] == "1"
  end

  def invalid_number?
    clean_number.length != 10
  end

  def valid_number?
    clean_number.length == 10
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end
end
