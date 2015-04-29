class Sieve
  FIRST = 2
  def initialize(max)
    @max = max
  end

  def primes
    @primes ||= sieve (FIRST..@max).to_a, FIRST
  end

  def sieve(list, start)
    while start 
      list.each { |n| n = 0 if n > start && n % start == 0 }
      start = list.detect { |n| n > start }
    end 
    list.reject!{ |n| n==0 }
  end
end
