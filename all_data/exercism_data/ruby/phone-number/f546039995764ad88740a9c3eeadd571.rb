class PhoneNumber
  VALID_LENGTH = 10
  INVALID_NUMBER = "0" * VALID_LENGTH

  attr_reader :phone_str

  def initialize(phone_str)
    @phone_str = phone_str
  end
  
  def number
    @numbers ||= 
      case sanitized
      when has_north_am_country_code?
        remove_country_code_from(sanitized)
      when length_invalid?
        INVALID_NUMBER
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
    -> (n) { n.length == VALID_LENGTH + 1 && n[0] == "1" }
  end

  def length_invalid?
    -> (n) { n.length != VALID_LENGTH }
  end

  def remove_country_code_from(n)
    n[1,VALID_LENGTH]
  end

  def prefix
    number[3,3]
  end
  
  def suffix
    number[6,4]
  end
  

  def sanitized
    @sanitized ||= phone_str.gsub(/\D/, "")
  end
end
