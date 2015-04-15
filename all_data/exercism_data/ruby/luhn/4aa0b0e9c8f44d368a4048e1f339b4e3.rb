class Luhn

  attr_reader :candidate

  def initialize(candidate)
    @candidate = candidate.to_s.reverse.split("")
  end

  def addends
    candidate.each_with_index.inject([]) do |result, position|
      result.unshift(multiply(position))
    end
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create(raw)
    check = checksum_digit_for(raw)
    luhn_string = raw.to_s + check.to_s
    luhn_string.to_i
  end

  private

  def multiply(position)
    digit, index = position
    digit = digit.to_i
    digit *= 2 if index.odd?
    digit -= 9 if digit > 10
    digit
  end

  def self.checksum_digit_for(raw)
    check = raw_checksum_for(raw)
    check = (check % 10)
    check = 10 - check unless check == 0
    check
  end

  def self.raw_checksum_for(raw)
    shifted_candidate = raw.to_s + "0"
    new(shifted_candidate).checksum
  end

end
