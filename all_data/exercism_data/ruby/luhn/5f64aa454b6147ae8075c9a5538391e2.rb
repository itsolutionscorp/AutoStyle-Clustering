class Luhn
  attr_reader :number

  def initialize n
    @number = n
  end

  def addends
    number.to_s
          .chars
          .map(&:to_i)
          .reverse
          .map.with_index { |n, i| i.odd? ? double(n) : n }
          .reverse
  end

  def checksum
    addends.reduce :+
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create n
    (0..9).each do |i|
      c = n.to_s.chars
      c << i.to_s
      luhn = Luhn.new(c.join.to_i)
      return luhn.number if luhn.valid?
    end
  end

  private

  def double n
    n *= 2
    n -= 9 if n >= 10
    n
  end
end
