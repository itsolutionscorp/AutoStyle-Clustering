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
    clean_number[0..2]
  end

  def to_s
    if us_number?
      "(#{area_code}) #{phone_number[3..5]}-#{phone_number[6..9]}"
    else
      "(#{phone_number[1..3]}) #{phone_number[4..6]}-#{phone_number[7..10]}"
    end
  end
end
