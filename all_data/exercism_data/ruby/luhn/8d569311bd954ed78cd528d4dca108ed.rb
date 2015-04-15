class Luhn
  def initialize(n)
    @n = n
  end

  def addends
    narray = @n.to_s.chars.map(&:to_i)
    narray.reverse.map.with_index(1) do |x, i|
      a = i % 2 == 0 ? x * 2 : x
      a > 10 ? a - 9 : a
    end.reverse
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create(n)
    x = n * 10
    x += 1 while !self.new(x).valid?
    x
  end
end
