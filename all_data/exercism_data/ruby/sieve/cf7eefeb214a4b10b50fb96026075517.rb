class Sieve
  FIRST = 2
  def initialize(max)
    @max = max
  end

  def primes
    @primes ||= sieve (FIRST..@max).to_a, FIRST
  end

  def sieve(list, start)
    list.reject! { |n| n > start && n % start == 0 }
    new_start = list.detect { |n| n > start }
    new_start ? sieve(list, new_start) : list
  end
end
