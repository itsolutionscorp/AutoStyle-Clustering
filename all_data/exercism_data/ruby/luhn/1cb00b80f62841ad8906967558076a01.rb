class Luhn
  def initialize(number)
    @digits = number.to_s.each_char.map { |c| c.to_i }
  end

  def self.create(number)
    new_number = number * 10
    luhn = new(new_number)
    new_number += (10 - luhn.checksum % 10) unless luhn.valid?
    new_number
  end

  def addends
    @digits.reverse.each_with_index.map do |number, index|
      index.even? ? number : double(number)
    end.reverse
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum % 10 == 0
  end

private

  def double(digit)
    limit(digit * 2)
  end

  def limit(digit)
    digit > 9 ? digit - 9 : digit
  end
end
