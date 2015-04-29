class Prime
  
  def self.nth(n)
    raise ArgumentError, 'Please use positive integers.' unless n > 0
    nth_prime = (Prime.first n).last
  end
  
end
