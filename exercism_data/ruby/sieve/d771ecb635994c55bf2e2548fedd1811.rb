class Sieve

  attr_reader :n

  def initialize(n)
    @n = n
  end

  def primes
    candidates = [*2..n]
    2.upto(Math.sqrt n).with_object([]) do |_, primes|
      primes << candidates.shift
      candidates.keep_if { |c| c % primes.last != 0 }
    end.push(*candidates)
  end

end
