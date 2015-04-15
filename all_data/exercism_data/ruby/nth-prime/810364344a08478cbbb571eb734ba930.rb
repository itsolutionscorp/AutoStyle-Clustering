class Prime
  def self.nth(position)
    raise ArgumentError,
         "zero is an invalid position" if position == 0
    primes = Primes.new
    until primes.size > position do 
      primes = primes.next
      p primes.size
    end
    primes.prime_at(position)
  end
end

class Primes

  def initialize(primez: nil)
    if primez == nil
      @primes = [2]
    else
      @primes = primez.primes + primez.seive
    end
    make_seive
  end

  attr_reader :primes, :seive

  def prime_at(number)
    @primes[number - 1]
  end

  def last
    @primes.last
  end

  def next
    @primes.each do |prime|
      eliminate_multiples_of(prime)
    end
    self.class.new(primez: self)
  end

  def size
    @primes.size
  end

  private

  def make_seive
    @seive ||= (lower_limit...upper_limit).to_a
  end

  def lower_limit
    last + 1
  end

  def upper_limit
    last * 2
  end

  def eliminate_multiples_of(this_prime)
    @seive = @seive - prime_multiples(this_prime)
  end

  def prime_multiples(this_prime)
    ss = seive_start(this_prime)
    se = seive_end(this_prime)
    (ss..se).to_a.map { |multiple| multiple * this_prime }
  end

  def seive_start(prime)
    @seive.first / prime
  end

  def seive_end(prime)
    @seive.last / prime
  end

end
