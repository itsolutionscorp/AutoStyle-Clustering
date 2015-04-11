class Sieve
  def initialize (n)
    @sieve = Array.new(n - 1, true)
    (2..n).each{|i|
      l = i - 2
      (@sieve[l += i] = false while l <= n) if @sieve[l]
    }
  end
  def primes
    @sieve.each_with_index.select{|p, i| p}.map{|p, i| i + 2}
  end
end
