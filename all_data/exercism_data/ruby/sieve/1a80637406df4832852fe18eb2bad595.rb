class Sieve
  def initialize(n)
    @range = (2..n).to_a
  end

  def primes(range = @range, n = 0, previous_iteration = [])
    return range if range == previous_iteration
    previous_iteration = range
    range = range - range[1 + n..-1].keep_if { |x| x % range[n] == 0 }
    primes(range, n + 1, previous_iteration)
  end
end
