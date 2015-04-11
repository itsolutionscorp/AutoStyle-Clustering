module PhoneNumber
  UNITED_STATES = /\A1?\d{10}\z/
  def self.new(digits)
    digits.tr!('^0-9', '')

    case digits
    when UNITED_STATES ; UnitedStatesPhoneNumber.new(digits[-10..-1])
    else                 InvalidNumber.new
    end
  end
end

class UnitedStatesPhoneNumber
  attr_reader :number
  def initialize(digits)
    @number = digits
  end

  def area_code
    number.slice(0..2)
  end

  def to_s
    "(#{area_code}) #{exchange_code}-#{subscriber_number}"
  end
  private
  attr_reader :digits

  def exchange_code
    number[3..5]
  end

  def subscriber_number
    number[6..10]
  end
end

class InvalidNumber
  NUMBER = '0000000000'
  def number
    NUMBER
  end
end
