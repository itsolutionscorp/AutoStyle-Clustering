class Luhn
  attr_reader :digits

  class << self
    def create(digits)
      num = new(digits)
      num.valid? ? num.digits : num.convert_to_luhn
    end
  end

  def initialize(digits)
    @digits = digits  
  end

  def addends
    formatted_digits.reverse.each_with_index.map do |digit, i|
      i % 2 != 0 ? encode(digit) : digit
    end.reverse
  end

  def checksum
    addends.reduce(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  def convert_to_luhn
    num = self.class.new(digits * 10)
    add_digit = num.valid? ? 0 : (10 - num.checksum % 10)
    (formatted_digits << add_digit).join.to_i
  end

  private

  def formatted_digits
    digits.to_s.chars.map(&:to_i)
  end

  def encode(digit)
    times_two = digit * 2
    times_two > 9 ? (times_two - 9) : times_two
  end
end


number = Luhn.new(123026)
p number.addends
p number.checksum
p number.convert_to_luhn
