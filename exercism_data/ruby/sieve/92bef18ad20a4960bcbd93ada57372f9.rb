# class Sieve
class Sieve
  attr_reader :n
  def initialize(n)
    @n = n
  end

  def primes
    l = (2..n).to_a
    p = 2
    while p <= n
      l.delete_if { |e| (e != p) && (e % p == 0) }
      p += 1
    end
    l
  end
end
