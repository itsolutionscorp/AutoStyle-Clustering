class Prime
  def self.nth(n)
    fail ArgumentError, 'n must be a positive integer' if n < 1
    test = 1
    loop do
      n -= 1 if prime? test
      break if n == 0
      (test == 1 || 2) ? test += 1 : test += 2
    end
    test
  end

  def self.prime?(num)
    return false if num == 1 || (num.even? && num != 2)
    return true if num == 2
    (3..Math.sqrt(num)).step(2) do |x|
      return false if num % x == 0
    end
    true
  end
end
