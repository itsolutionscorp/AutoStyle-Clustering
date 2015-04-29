class PhoneNumber
  VALID_LENGTH = 10

  attr_reader :phone_str

  def initialize(phone_str)
    @phone_str = phone_str
  end
  
  def number
    @numbers ||= 
      if has_north_am_country_code?
        sanitized[1,VALID_LENGTH]
      elsif length_invalid?
        ("0" * VALID_LENGTH)
      else
        sanitized
      end
  end

  def to_s
    "(#{area_code}) #{prefix}-#{suffix}"
  end

  def area_code
    number[0,3]
  end

  private

  def has_north_am_country_code?
    sanitized.length == VALID_LENGTH + 1 && sanitized[0] == "1"
  end

  def prefix
    number[3,3]
  end
  
  def suffix
    number[6,4]
  end
  
  def length_invalid?
    sanitized.length != VALID_LENGTH
  end

  def sanitized
    @sanitized ||= phone_str.gsub(/\D/, "")
  end
end
