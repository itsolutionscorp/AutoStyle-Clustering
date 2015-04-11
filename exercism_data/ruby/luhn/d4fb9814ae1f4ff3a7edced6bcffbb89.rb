class Luhn
  def initialize(n)
    @n = n
  end

  def addends
    res = []
    n = @n
    count = 1
    while n > 0
      if count % 2 == 0
        x = (n % 10) * 2
        x -= 9 if x > 9
        res.insert(0, x)
      else
        res.insert(0, n % 10)
      end
      n /= 10
      count += 1
    end
    res
  end
  
  def checksum
    addends.reduce(:+)
  end

  def valid?
    (checksum % 10).zero?
  end

  def self.create(n)
    0.upto(9).each do |i|
      n2 = n * 10 + i
      return n2 if Luhn.new(n2).valid?
    end
    0
  end
end
