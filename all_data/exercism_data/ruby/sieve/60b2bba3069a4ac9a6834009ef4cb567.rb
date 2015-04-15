class Sieve
  attr_reader :range

  def initialize n
    @range = 2..n
  end

  def primes
    r = range.to_a
    r[0..Math.sqrt(r.last)].each do |n|
      multiples = (n..r.last).step(n).to_a
      multiples.shift
      r -= multiples
    end
    r
  end
end
