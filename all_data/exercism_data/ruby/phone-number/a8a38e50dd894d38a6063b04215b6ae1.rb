module Phone
  def self.new input_string
    PhoneNumberParser.new(input_string).parsed
  end
end

class PhoneNumberParser
  def initialize input_string
    digits = input_string.delete('^0-9').chars
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
    national_numbers.lazy
      .select { |type| digits.take(type.prefix.length) == type.prefix }
      .map    { |type| type.new(digits.drop(type.prefix.length)) }
      .find { |parsed| parsed.valid? }
  end

  def national_numbers
    [ USPhoneNumberDigits, NLPhoneNumberDigits]
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

  def number
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
