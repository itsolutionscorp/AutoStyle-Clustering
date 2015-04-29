class Prime
  def self.nth(n)
    raise ArgumentError if n < 1

    prime = 2
    (n - 1).times { prime = next_prime(prime + 1) }
    prime
  end

  def self.next_prime(n)
    n = n.next until is_prime?(n)
    n
  end

  def self.is_prime?(n)
    (2..n**(0.5)).all? { |divisor| n % divisor != 0 }
  end
end
