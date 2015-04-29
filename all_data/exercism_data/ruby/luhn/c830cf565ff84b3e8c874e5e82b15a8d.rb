class Luhn
  def self.create number
    luhn = Luhn.new(number * 10)
    number * 10 + (10 - luhn.checksum) % 10
  end

  def initialize number
    @number = number
  end

  def addends
    digits.reverse.each_with_index.map do |digit, index|
      next digit unless index.odd?

      if digit < 5
        digit * 2
      else
        digit * 2 - 9
      end
    end.reverse
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  private

  def digits
    @number.to_s.chars.map(&:to_i)
  end
end
