class PhoneNumberParser
  def initialize digits
    @digits = digits.map(&:to_i)
  end

  def parsed
    parsed_digits = parsed_international || parsed_default
    if parsed_digits.valid?
      parsed_digits
    else
      InvalidDigits.new digits
    end
  end

  private

  def parsed_default
    default.new(digits)
  end

  def parsed_international
    national_numbers.lazy.map do |number_type|
      if digits.take(number_type.prefix.length) == number_type.prefix
        number_type.new digits.drop(number_type.prefix.length)
      end
    end.find { |parsed| parsed && parsed.valid? }
  end

  def national_numbers
    [
      USPhoneNumberDigits,
      NLPhoneNumberDigits
    ]
  end

  def default
    USPhoneNumberDigits
  end

  def digits
    @digits
  end
end


class PhoneNumberDigits
  def initialize digits
    @digits = digits
  end

  def to_s
    format formatting.gsub('x', '%d'), *digits
  end

  def without_formatting
    digits.join ''
  end

  def area_code
    digits.take(area_code_length).join
  end

  private

  def digits
    @digits
  end
end

class InvalidDigits < PhoneNumberDigits
  def formatting
    ''
  end

  def digits
    [0]*10
  end

  def area_code_length
    0
  end
end

class USPhoneNumberDigits < PhoneNumberDigits
  LENGTH = 10

  def self.prefix
    [1]
  end

  def valid?
    digits.length == LENGTH
  end

  private

  def formatting
    "(xxx) xxx-xxxx"
  end

  def area_code_length
    3
  end
end

class NLPhoneNumberDigits < PhoneNumberDigits
  LENGTH = 9 # it's actually ten, but always starts with 0,
             # which is replaced by international prefix in
             # the international version

  def self.prefix
    [3,1]
  end

  def valid?
    digits.length == LENGTH
  end

  private

  def formatting
    "0" +
      "x" * (area_code_length - 1 )+
      " " +
      "x" * (LENGTH - area_code_length + 1)
  end

  def area_code_length
    if mobile?
      2
    elsif city?
      3
    else
      4
    end
  end

  def mobile?
    digits[0] == 6
  end

  def city?
    digits[2] == 0
  end

end

class Phone
  def initialize input_string
    @input_string = input_string
  end

  def number
    digits.without_formatting
  end

  def area_code
    digits.area_code
  end

  def to_s
    digits.to_s
  end

  private

  def digits
    PhoneNumberParser.new(@input_string.delete('^0-9').chars).parsed
  end
end
