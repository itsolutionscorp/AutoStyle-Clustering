def is_prime?(n)
  (2..Math.sqrt(n)).each do |x|
    return false if n%x == 0
  end
  true
end

module Prime

  @@primes = {1 => 2}
  @@greatest_value = 2

  def self.nth(n)
    raise ArgumentError, "Argument must be greater than 0" unless n > 0
    return @@primes[n] if @@primes[n]
    until @@primes[n]
      next_prime
    end
    @@primes[n]
  end

  private

  def self.next_prime
    x = @@greatest_value + 1
    until is_prime?(x)
      x += 1
    end
    @@primes[@@primes.length + 1] = x
    @@greatest_value = x
  end

end

##### the easy way: #####
# require 'prime'
# class Prime
#   def self.nth(n)
#     raise ArgumentError, "Argument must be greater than 0" unless n > 0
#     self.first(n).last
#   end
# end
