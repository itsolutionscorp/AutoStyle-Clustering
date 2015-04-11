class Prime
  def self.prime?(n)
    n != 1 && !(2..Math.sqrt(n)).any?{ |d| n % d == 0 }
  end

  def self.nth(n)
    raise ArgumentError if n == 0
    current = 1
    while n > 0
      current += 1
      n -= 1 if prime?(current)
    end
    current
  end
end
