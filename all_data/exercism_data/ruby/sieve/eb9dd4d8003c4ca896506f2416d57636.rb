class Sieve
  attr_reader :range

  def initialize n
    @range = 2..n
  end

  def primes
    r = range.to_a
    r[0...-1].each do |n|
      multiples = (n..range.last).step(n).with_object([]) do |i, a|
        a << i
      end
      multiples.shift
      r -= multiples
    end
    r
  end
end
