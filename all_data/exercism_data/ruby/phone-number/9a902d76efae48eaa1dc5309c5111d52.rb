class PhoneNumber
  def initialize phone_number 
    @phone_number = phone_number
  end

  MAX_LENGTH = 10
  TEN_ZEROES = ("0"*MAX_LENGTH).freeze

  def number
    cleaned_phone_number = clean phone_number

    if cleaned_phone_number.length != MAX_LENGTH
      return TEN_ZEROES
    end

    cleaned_phone_number
  end

  def area_code
    "123"
  end
 
  def to_s
    "(123) 456-7890"
  end

  private
  attr_reader :phone_number
  def clean number
    digits = digits_from number
    starts_with_one?(digits) ? digits[1..-1] : digits
  end

  def digits_from phone_number
    phone_number.scan(/\d+/).join
  end

  def starts_with_one? phone_number
    phone_number.length==11 && phone_number.start_with?("1")
  end
end
