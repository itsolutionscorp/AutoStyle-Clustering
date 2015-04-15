class Luhn
  def Luhn.create (n)
    (0..9).each do |i|
      return n * 10 + i if Luhn.new(n * 10 + i).valid?
    end
  end
  def initialize (n)
    @n = n
  end
  def addends
    n, res, double = @n, [], false
    while n > 0
      k, n = n % 10, n / 10
      res << -> x {x > 9 ? x - 9 : x}[double ? 2 * k : k]
      double = !double
    end
    res.reverse
  end
  def checksum
    addends.reduce(:+)
  end
  def valid?
    (checksum % 10).zero?
  end
end
