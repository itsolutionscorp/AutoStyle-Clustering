class Sieve
  def initialize(limit)
    @limit = limit
  end
  
  def primes
    numbers = (2..@limit).to_a
    numbers.each { |num| numbers.reject! { |x| x % num == 0 && x != num  } }
    numbers
  end
end
