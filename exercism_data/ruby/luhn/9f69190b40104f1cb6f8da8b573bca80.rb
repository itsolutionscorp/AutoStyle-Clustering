class Luhn

  attr_reader :number

  def initialize number
    @number = number
  end

  def self.create number
    luhn = Luhn.new(number*10)
    return luhn.number if luhn.valid?
    luhn.number + 10 - luhn.checksum % 10
  end

  def addends
    temp = number.to_s.split('').map(&:to_i).reverse
    (1..temp.length-1).step(2) do |i|
      if temp[i]*2 < 10
        temp[i] *= 2
      else
        temp[i] = temp[i] * 2 - 9
      end
    end
    temp.reverse
  end

  def checksum
    addends.inject :+
  end

  def valid?
    checksum % 10 == 0
  end

end
