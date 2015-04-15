class Primes
  include Enumerable

  class << self
    alias :upto :new
  end

  def initialize(max)
    @max = max
    @sieve = build_sieve
  end

  def each(&block)
    @sieve.each_with_index do |prime, n|
      block.call(n) if prime
    end
  end

  private

  def build_sieve
    sieve = Array.new(@max+1, true)
    sieve[0] = sieve[1] = false

    2.upto(Math.sqrt(@max)) do |n|
      2.upto(@max / n) do |f|
        sieve[n * f] = false
      end
    end

    sieve
  end
end

class PrimeFactors
  def self.for(n)
    self.new(n).factors
  end

  def initialize(target)
    @target = target
  end

  def factors
    if first_factor
      [first_factor, *PrimeFactors.for(@target / first_factor)]
    else
      []
    end
  end

  private

  def first_factor
    @first_factor ||= Primes.upto(@target).find{ |p| @target % p == 0 }
  end
end
