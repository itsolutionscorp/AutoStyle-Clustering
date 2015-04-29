class Sieve
  def initialize(limit)
    @limit = limit
  end
  def primes
    index = 0
    numlist = *(2..@limit)
    primes = Array.new
    while  numlist.length > 0
      primes << numlist.shift
      numlist.each{|num| numlist.delete(num) if num % primes.last == 0}
    end
    primes
  end
end
