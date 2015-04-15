class Sieve
  def initialize(n)
    @length = n
  end

  def primes
    numbers = [*2..@length]
    numbers.each do |n|
      numbers.delete_if { |x| x>n && x%n == 0 }
    end
  end

end
