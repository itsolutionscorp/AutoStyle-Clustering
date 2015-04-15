class Prime
  def self.nth(n)
    raise ArgumentError if n <= 0
    prime = 1
    n.times do |i|
      prime += 1
      until is_prime?(prime)
        prime += 1
      end
    end
    prime
  end

  def self.is_prime?(num)
    return false if num < 2
    (2..Math.sqrt(num)).each do |n|
      if num % n == 0
        return false
      end
    end
    true
  end
end
