class Sieve
  def initialize(n)
    @n = n
  end

  def primes
    a = (2..@n).to_a
    a.each{ |x|
      a = a.select { |y| y == x || y % x != 0 }
    }
    a
  end
end
