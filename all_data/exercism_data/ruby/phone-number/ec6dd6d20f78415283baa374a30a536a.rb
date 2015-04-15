class PhoneNumber
  def initialize(phone_number, 
                 cleaner=PhoneNumberCleaner.new)

    @phone_number = phone_number
    @cleaner = cleaner
  end

  MAX_LENGTH = 10
  INVALID_NUMBER = ("0"*MAX_LENGTH).freeze

  def number
    cleaned_phone_number ||= cleaner.clean phone_number

    if cleaned_phone_number.length != MAX_LENGTH
      return INVALID_NUMBER
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
  attr_reader :phone_number, :cleaner

  class PhoneNumberCleaner
    def clean number
      digits = digits_from number

      if full_length_north_american_number?(digits) 
        digits[1..-1]
      else
        digits
      end
    end
  
    def digits_from phone_number
      phone_number.scan(/\d+/).join
    end
  
    def full_length_north_american_number? phone_number
      phone_number.length==11 && phone_number.start_with?("1")
    end
  end
end
