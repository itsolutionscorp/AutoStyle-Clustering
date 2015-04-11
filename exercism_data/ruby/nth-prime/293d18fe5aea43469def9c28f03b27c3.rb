class Prime
  @@primes = [2]
  def self.nth(num)
    raise(ArgumentError) if num < 1
    
    current = @@primes.last
    while @@primes.count < num
      current += 1
      unless @@primes.any? {|p| current % p == 0}
        @@primes << current
      end
    end
    
    @@primes[num - 1]
  end
end
