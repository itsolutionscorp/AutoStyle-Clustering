class Luhn
  def self.create(number)
    number *= 10
    luhn = new(number)
    number += 10 - luhn.mod unless luhn.valid?
    number
  end

  def initialize(number)
    @number = number
  end

  def addends
    number.to_s.reverse.each_char.with_index.reduce([]) do |digits, (char, index)|
      num = char.to_i
      if index.odd?
        num *= 2
        num -= 9 if num >= 10
      end
      digits << num
    end.reverse
  end

  def checksum
    addends.reduce(0, :+)
  end

  def mod
    checksum % 10
  end

  def valid?
    mod == 0
  end

  private

  attr_reader :number
end
