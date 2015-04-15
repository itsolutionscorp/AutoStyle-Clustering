class Luhn
  def initialize(number)
    @digits = number.to_s.chars.map(&:to_i)
  end

  def addends
    [].tap do |arr|
      each_digit { |digit| arr << digit }
    end
  end

  def checksum
    addends.inject(0, :+)
  end

  def valid?
    checksum % 10 == 0
  end

  def checkdigit
    (checksum * 9).to_s.chars.last.to_i
  end

  def self.create(number)
    luhn = new(number * 10)

    number * 10 + luhn.checkdigit
  end

  private

  def each_digit
    @digits.each.with_index do |digit, idx|
      digit = (digit * 2) if idx % 2 != (@digits.length - 1) % 2
      digit -= 9 if digit >= 10
      yield digit
    end
  end
end
