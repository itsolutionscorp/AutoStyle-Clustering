class Luhn
  def initialize(number)
    @number = number 
  end

  def addends
    result = []
    @number.to_s.chars.reverse.each_with_index do |digit, i|
      i.odd? ? result << dubble_up(digit.to_i) : result << digit.to_i
    end
    result.reverse
  end

  def checksum
    addends.inject(&:+)
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create(num)
    return num * 10 if Luhn.new(num * 10).valid?

    (num.to_s + (10 - Luhn.new(num * 10).checksum % 10).to_s).to_i
  end

  private

  def dubble_up(digit)
    digit >= 5 ? digit * 2 - 9 : digit * 2
  end


end
