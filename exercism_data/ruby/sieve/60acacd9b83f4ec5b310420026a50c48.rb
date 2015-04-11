class Sieve
  def initialize(n)
    @n = n
  end

  def primes
    a = [*2..@n]
    2.upto(@n) do |i|
      a.delete_if { |x| x != i && x % i == 0 }
    end
    a
  end
end
