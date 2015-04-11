require 'prime'

class Sieve

  def initialize(n)
    @n = n
  end

  def primes
    out = []
    @n.times do |n|
      num = n + 1
      out << num if Prime.prime?(num)
    end
    out
  end

end
