class Luhn
  def initialize(n)
    @n = n
  end

  def addends
    n = @n
    addends = []
    until n.zero?
      n, last_digit = n.divmod(10)
      last_digit *= 2 if addends.length % 2 == 1
      last_digit -= 9 if last_digit > 9
      addends.unshift(last_digit)
    end
    addends
  end

  def checksum
    addends.reduce(0, :+)
  end

  def valid?
    checksum.modulo(10).zero?
  end

  def self.create(n)
    n *= 10
    checksum = Luhn.new(n).checksum
    last_checksum_digit = checksum % 10
    difference = (10 - last_checksum_digit) % 10
    n + difference
  end
end
