class Phone
  def initialize digits
    self.digits = digits
  end

  def number
    valid? ? digits : invalid_number
  end

  def area_code
    number[0..2]
  end

  def to_s
    number.gsub /(\d{3})(\d{3})(\d{4})/, '(\1) \2-\3'
  end

  private

  attr_reader :digits

  def digits= digits
    @digits = DigitsNormalizer.normalize digits
  end

  def valid?
    digits.size == 10
  end

  def invalid_number
    "0000000000"
  end

  class DigitsNormalizer
    def self.normalize digits
      normalized = digits.gsub /\D/, ""
      normalized = normalized[1..-1] if includes_country_code normalized
      normalized
    end

    def self.includes_country_code digits
      digits.size == 11 && digits[0] == "1"
    end
  end
end
