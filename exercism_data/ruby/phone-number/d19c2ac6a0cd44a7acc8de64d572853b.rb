class PhoneNumber
  def initialize(sequence)
    @sequence = sequence
  end

  def number
    Validator.format(@sequence)
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{exchange}-#{line}"
  end

  private

  def exchange
    number[3..5]
  end

  def line
    number[6..-1]
  end
end

class Validator
  def initialize(sequence)
    @sequence = sequence
  end

  def self.format(sequence)
    new(sequence).format
  end

  def format
     valid? ? national : invalid
  end

  private

  def valid?
    national.length == 10
  end

  def national
    country_code? ? digits[1..-1] : digits
  end

  def country_code?
    digits.length > 10 && digits.start_with?('1')
  end

  def digits
    @sequence.tr('^0-9', '')
  end

  def invalid
    "0000000000"
  end
end
