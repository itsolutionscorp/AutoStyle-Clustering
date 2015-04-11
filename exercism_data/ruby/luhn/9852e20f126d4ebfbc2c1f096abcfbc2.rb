class Luhn
  attr_reader :number

  def self.create(number)
    (0..9).zip([number * 10].cycle).find {|a, b| new(a + b).valid? }.inject(:+)
  end

  def initialize(number)
    @number = number
  end

  def addends
    digits.reverse.map.with_index {|digit, index|
      index % 2 == 1 ? process_digit(digit) : digit 
    }.reverse
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  private

  def digits
    @digits = number.to_s.chars.map(&:to_i)
  end

  def process_digit(digit)
    (n = digit * 2) - (n / 10) * 9
  end
end
