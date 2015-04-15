class Luhn
  def self.create(number_without_checksum)
    0.upto(9) do |checksum|
      candidate = "#{number_without_checksum}#{checksum}".to_i
      return candidate if new(candidate).valid?
    end
  end

  def initialize(number)
    @number = number
  end

  def addends
    digits.reverse.each_with_index.map { |digit, index|
      digit *= 2 if index.odd?
      digit -= 9 if digit > 9
      digit
    }.reverse
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    (checksum % 10).zero?
  end

  private

  def digits
    @number.to_s.chars.map(&:to_i)
  end
end
